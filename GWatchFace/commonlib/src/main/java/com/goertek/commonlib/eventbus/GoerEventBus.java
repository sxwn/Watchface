package com.goertek.commonlib.eventbus;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.ArrayMap;
import android.util.Log;

import com.goertek.commonlib.utils.LogUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.Nullable;

public class GoerEventBus {
    private static final int MODIFIERS_IGNORE = Modifier.ABSTRACT | Modifier.STATIC;

    private UIHandler uiHandler;
    private BackgroundRunnable backgroundRunnable;
    private Map<Class<?>, CopyOnWriteArrayList<MethodSub>> mSubWithType;
    private Map<Object, List<Class<?>>> mTypeBySub;
    private static GoerEventBus mInstance;

    private ThreadLocal<ThreadLocalState> currentThreadLocalState = new ThreadLocal<ThreadLocalState>() {
        @Nullable
        @Override
        protected ThreadLocalState initialValue() {
            return new ThreadLocalState();
        }
    };

    public static GoerEventBus getDefault() {
        if (mInstance == null) {
            synchronized (GoerEventBus.class) {
                if (mInstance == null) {
                    mInstance = new GoerEventBus();
                }
            }
        }
        return mInstance;
    }

    private GoerEventBus() {
        mSubWithType = new HashMap<>();
        mTypeBySub = new HashMap<>();
        uiHandler = new UIHandler(this, Looper.getMainLooper());
        backgroundRunnable = new BackgroundRunnable(this);
    }

    /**
     * 注册
     *
     * @param object
     */
    public void register(Object object) {

        if (object == null){
            return;
        }
        //获取被观察者类,类型
        Class<?> subClass = object.getClass();
        //获取被观察者注册的所有@sub方法
        List<FinderMethod> methods = obtainSubMethod(subClass);

        for (FinderMethod method : methods) {
            subscription(object, method);
        }

    }

    private void subscription(Object object, FinderMethod method) {
        Class<?> eventType = method.eventType;
        MethodSub methodSub = new MethodSub(object, method);
        CopyOnWriteArrayList<MethodSub> findSubMethods = mSubWithType.get(eventType);
        if (findSubMethods == null) {
            findSubMethods = new CopyOnWriteArrayList<>();
            mSubWithType.put(eventType, findSubMethods);
        }
        findSubMethods.add(methodSub);

        List<Class<?>> mClassSub = mTypeBySub.get(object);
        if (mClassSub == null) {
            mClassSub = new ArrayList<>();
            mTypeBySub.put(object, mClassSub);
        }
        mClassSub.add(eventType);
    }

    /**
     * 获取所有@sub方法
     *
     * @param subClass
     * @return
     */
    private List<FinderMethod> obtainSubMethod(Class<?> subClass) {
        final CopyOnWriteArrayList<FinderMethod> subMethod = new CopyOnWriteArrayList<>();
        final Method[] methods = subClass.getDeclaredMethods();
        if (methods.length < 1){
            LogUtil.e("obtainSubMethod", "methods.length = 0");
            return null;
        }
        for (Method method : methods) {
            int modifiers = method.getModifiers();
            if ((modifiers & Modifier.PUBLIC) != 0 && (modifiers & MODIFIERS_IGNORE) == 0) {
                //PUBLIC
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {//单个参数
                    Sub sub = method.getAnnotation(Sub.class);
                    if (sub != null) {
                        //添加方法
                        ThreadMode threadMode = sub.threadMode();
                        Class<?> eventType = parameterTypes[0];
                        subMethod.add(new FinderMethod(method, eventType, threadMode));
                    }
                }
//                else {
//                    String methodName = method.getDeclaringClass().getName() + "." + method.getName();
//                    throw new RuntimeException("@Sub method " + methodName +
//                            "must have exactly 1 parameter but has " + parameterTypes.length);
//                }
            }
            else {
                LogUtil.e("GoerEventBus","modifiers");
                String methodName = method.getDeclaringClass().getName() + "." + method.getName();
                throw new RuntimeException(methodName +
                        " is a illegal @Sub method: must be public, non-static, and non-abstract");
            }
        }
        return subMethod;
    }

    /**
     * 解注册
     *
     * @param object
     */
    public void unregister(Object object) {
        List<Class<?>> eventLists = mTypeBySub.get(object);
        if (eventLists != null) {
            for (Class<?> eventType : eventLists) {
                unSubMethod(object, eventType);
            }
            mTypeBySub.remove(object);
        }
    }

    public ExecutorService getExecutorService() {
        return Executors.newCachedThreadPool();
    }

    /**
     * 反注册方法
     *
     * @param object
     * @param eventType
     */
    private void unSubMethod(Object object, Class<?> eventType) {
        CopyOnWriteArrayList<MethodSub> subs = mSubWithType.get(eventType);
        if (subs != null) {
            Iterator<MethodSub> iterator = subs.iterator();
            int index = 0;
            while (iterator.hasNext()) {
                MethodSub sub = iterator.next();
                if (sub.subscriber == object) {
                    break;
                }
                index++;
            }
            subs.remove(index);
        }
    }

    /**
     * 是否在主线程(UI)
     *
     * @return
     */
    private boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * 发送事件
     *
     * @param object
     */
    public void send(Object object) {
        ThreadLocalState threadLocalState = currentThreadLocalState.get();
        List<Object> eventQueue = threadLocalState.eventQueue;
        eventQueue.add(object);

        threadLocalState.isMainThread = isMainThread();
        if (!threadLocalState.canceled) {
            try {
                while (!eventQueue.isEmpty()) {
                    postSingleEvent(eventQueue.remove(0), threadLocalState);
            }
            } finally {
                threadLocalState.isMainThread = false;
            }

        }
        //根据eventType获取

    }

    private void postSingleEvent(Object eventObject, ThreadLocalState threadLocalState) {
        Class<?> eventClass = eventObject.getClass();
        CopyOnWriteArrayList<MethodSub> subs = mSubWithType.get(eventClass);
        if (subs != null && !subs.isEmpty()) {
            for (MethodSub sub : subs) {
                ThreadMode mode = sub.method.threadMode;
                switch (mode) {
                    case UI:
                        if (threadLocalState.isMainThread) {
                            invokeSubMethod(sub, eventObject);
                        } else {
                            uiHandler.addMessage(new PostMessage(sub, eventObject));
                        }
                        break;
                    case WORK:
                        if (threadLocalState.isMainThread) {
                            backgroundRunnable.addMessage(new PostMessage(sub, eventObject));
                        } else {
                            invokeSubMethod(sub, eventObject);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void invokeSubMethod(MethodSub subscriber, Object eventObject) {
        try {
            subscriber.method.method.invoke(subscriber.subscriber, eventObject);
        } catch (IllegalAccessException e) {
            Log.d("EventBus", "error msg:" + e.getMessage());
        } catch (InvocationTargetException e) {
            Log.d("EventBus", "error msg:" + e.getMessage());
        }
    }

    public void invokeSubMethod(PostMessage postMessage) {
        invokeSubMethod(postMessage.subscriber, postMessage.eventObject);
    }

    private static class UIHandler extends Handler {
        GoerEventBus eventBus;
        List<PostMessage> messageList;
        boolean handleActive = false;

        UIHandler(GoerEventBus bus, Looper looper) {
            super(looper);
            eventBus = bus;
            messageList = new ArrayList<>(0);
        }

        void addMessage(PostMessage msg) {
            messageList.add(msg);
            if (!handleActive) {
                handleActive = true;
                if (!sendMessage(obtainMessage())) {
                    throw new RuntimeException("send message error!");
                }
            }
        }

        @Override
        public void handleMessage(Message msg) {
            Iterator<PostMessage> iterator = messageList.iterator();
            while (true) {
                PostMessage pMsg = iterator.next();
                if (pMsg == null) {
                    handleActive = false;
                    break;
                }
                eventBus.invokeSubMethod(pMsg);
                iterator.remove();
            }
        }
    }

    private static class BackgroundRunnable implements Runnable {
        GoerEventBus eventBus;
        List<PostMessage> messageList;
        boolean handleActive = false;

        BackgroundRunnable(GoerEventBus bus) {
            eventBus = bus;
            messageList = new ArrayList<>(0);
        }

        public void addMessage(PostMessage postMessage) {
            messageList.add(postMessage);
            if (!handleActive) {
                handleActive = true;
                eventBus.getExecutorService().execute(this);
            }
        }

        @Override
        public void run() {
            Iterator<PostMessage> iterator = messageList.iterator();
            while (true) {
                PostMessage pMsg = iterator.next();
                if (pMsg == null) {
                    handleActive = false;
                    break;
                }
                eventBus.invokeSubMethod(pMsg);
                iterator.remove();
            }
        }
    }

    private class FinderMethod {
        Method method;
        Class<?> eventType;
        ThreadMode threadMode;


        public FinderMethod(Method method, Class<?> eventType, ThreadMode threadMode) {
            this.method = method;
            this.eventType = eventType;
            this.threadMode = threadMode;
        }
    }

    private class MethodSub {
        FinderMethod method;
        Object subscriber;

        public MethodSub(Object subscriber, FinderMethod method) {
            this.method = method;
            this.subscriber = subscriber;
        }
    }

    private class ThreadLocalState {
        final List<Object> eventQueue = new ArrayList<>();
        boolean isMainThread;
        boolean canceled;
    }

    private class PostMessage {
        public MethodSub subscriber;
        public Object eventObject;

        public PostMessage(MethodSub subscriber, Object eventObject) {
            this.subscriber = subscriber;
            this.eventObject = eventObject;
        }
    }
}
