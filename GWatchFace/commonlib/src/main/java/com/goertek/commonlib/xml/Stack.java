package com.goertek.commonlib.xml;

/**
 *  类型存储栈
 * <p>
 *  @author: carey.yu
 *  @version: 1.0.0
 *  @since: 2019/7/12
 */

final class Stack<T> {
    private Object[] array = new Object[32];
    private int size = 0;

    Stack() {
    }

    public T peek() {
        return (T) array[size - 1];
    }

    public int size() {
        return size;
    }

    public T get(int i) {
        return (T) array[i];
    }

    public void drop() {
        size--;
    }

    public int cleanup(int i) {
        return cleanup(i, size);
    }

    public int cleanup(int index, int count) {
        int tempSize = size;
        if (count < tempSize) {
            for (int i = count; i < tempSize; i++) {
                array[i - index] = array[i];
            }
            size -= index;
        } else {
            size = tempSize - ((index - count) + tempSize);
        }
        if (size < 0) {
            size = 0;
        }
        return count - index;
    }

    public void fix(T t) {
        int newSize = --size;
        if (size > 0 && array[newSize - 1] == t) {
            size = newSize - 1;
        }
    }

    private void ensureStack() {
        int i = size;
        Object[] objArr = array;
        if (i == objArr.length) {
            Object[] newArray = new Object[i * 2];
            System.arraycopy(objArr, 0, newArray, 0, i);
            array = newArray;
        }
    }

    public void push(T t) {
        ensureStack();
        Object[] objArr = array;
        objArr[size++] = t;
    }

    public T pop() {
        ensureStack();
        if (size == 0) {
            return null;
        }
        T element = peek();
        array[--size] = null;
        return element;
    }

    public void pushAt(int index, T t) {
        if (index < 0) {
            index = 0;
        }
        ensureStack();
        Object[] objArr = array;
        for (int i = size - 1; i >= index; i--) {
            objArr[i + 1] = objArr[i];
        }
        objArr[index] = t;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            sb.append('>');
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        }
        return sb.toString();
    }

}

