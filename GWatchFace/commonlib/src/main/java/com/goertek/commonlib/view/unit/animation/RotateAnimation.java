package com.goertek.commonlib.view.unit.animation;

import java.util.Iterator;
import java.util.List;

/**
 * 旋转效果
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/09
 */
public class RotateAnimation {
    private static final String TAG = "RotateAnimation";

    private long mInitTime = 0L;

    private List<IMotion> mMotions;

    private float[] mRotateDegree;

    private List<String> mRotateMotionType;

    private float[] mRotateTime;

    private long mTotalTime = 0L;

    public RotateAnimation(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, List<String> paramList) {
        this.mRotateDegree = paramArrayOfFloat1;
        this.mRotateTime = paramArrayOfFloat2;
        this.mRotateMotionType = paramList;
    }

    public float getDegree() {
        long motionTime;
        float motionDegree;
        long mInitTime = this.mInitTime;
        float degree = 0.0F;
        if (mInitTime == 0L)
            return 0.0F;
        mInitTime = System.currentTimeMillis() - this.mInitTime;
        if (mInitTime < 0L)
            return 0.0F;
        mInitTime %= this.mTotalTime;
        int motion = this.mMotions.size();
        Iterator iterator = this.mMotions.iterator();
        while (true) {
            motionDegree = degree;
            motionTime = mInitTime;
            if (iterator.hasNext()) {
                IMotion iMotion = (IMotion) iterator.next();
                float f = (float) mInitTime;
                motionDegree = degree + iMotion.getForwardDistance(f);
                motionTime = (long) (f - iMotion.getTotalTime());
                degree = motionDegree;
                mInitTime = motionTime;
                if (motionTime < 0L)
                    break;
                continue;
            }
            break;
        }
        degree = motionDegree;
        if (motionTime > 0L) {
            motion--;
            while (true) {
                degree = motionDegree;
                if (motion >= 0) {
                    IMotion iMotion = mMotions.get(motion);
                    degree = (float) motionTime;
                    motionDegree -= iMotion.getReverseDistance(degree);
                    motionTime = (long) (degree - iMotion.getTotalTime());
                    if (motionTime < 0L)
                        return motionDegree;
                    motion--;
                    continue;
                }
                break;
            }
        }
        return degree;
    }
}
