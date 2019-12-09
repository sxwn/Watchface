package com.goertek.commonlib.service;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.wearable.watchface.WatchFaceService;
import android.util.Log;
import android.view.Choreographer;
import android.view.SurfaceHolder;

public abstract class GoerCanvasWatchFaceService extends WatchFaceService {

    public class Engine extends WatchFaceService.Engine {
        private boolean mDrawRequested;
        private boolean mDestroyed;
        private final Choreographer mChoreographer = Choreographer.getInstance();
        private final Choreographer.FrameCallback mFrameCallback = new Choreographer.FrameCallback() {
            public void doFrame(long frameTimeNs) {
                if (!mDestroyed) {
                    if (mDrawRequested) {
                        draw(getSurfaceHolder());
                    }

                }
            }
        };
        private final Handler mHandler = new Handler() {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        invalidate();
                    default:
                }
            }
        };

        public void onDestroy() {
            this.mDestroyed = true;
            this.mHandler.removeMessages(0);
            this.mChoreographer.removeFrameCallback(this.mFrameCallback);
            super.onDestroy();
        }

        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            this.invalidate();
        }

        public void onSurfaceRedrawNeeded(SurfaceHolder holder) {
            super.onSurfaceRedrawNeeded(holder);
            this.draw(holder);
        }

        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
            this.invalidate();
        }

        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            if (!visible) {
                this.invalidate();
            }
        }

        public void invalidate() {
            if (!this.mDrawRequested) {
                this.mDrawRequested = true;
                this.mChoreographer.postFrameCallback(this.mFrameCallback);
            }

        }

        public void postInvalidate() {
            this.mHandler.sendEmptyMessage(0);
        }

        public void onDraw(Canvas canvas, Rect bounds) {
        }

        private void draw(SurfaceHolder holder) {
            mDrawRequested = false;
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                try {
                    if (isVisible()) {
                        onDraw(canvas, holder.getSurfaceFrame());
                    }
                    /*else {
                        canvas.drawColor(-16777216);
                    }*/
                } finally {
                    holder.unlockCanvasAndPost(canvas);
                }

            }
        }
    }
}
