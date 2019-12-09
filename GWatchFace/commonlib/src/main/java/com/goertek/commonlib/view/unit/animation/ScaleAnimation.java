package com.goertek.commonlib.view.unit.animation;

import java.util.Iterator;
import java.util.List;

/**
 * 缩放效果
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/09
 */
public class ScaleAnimation {
    private long mInitTime = 0L;

    private List<IMotion> mMotions;

    private float[] mScaleAmount;

    private List<String> mScaleMotionType;

    private float[] mScaleTime;

    private long mTotalTime = 0L;

    public ScaleAnimation(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, List<String> paramList) {
        this.mScaleAmount = paramArrayOfFloat1;
        this.mScaleTime = paramArrayOfFloat2;
        this.mScaleMotionType = paramList;
    }

    public float getAmount() {
        long motionTime;
        float motionDistance;
        if (this.mInitTime == 0L)
            return 0.0F;
        long scalTime = System.currentTimeMillis() - this.mInitTime;
        if (scalTime < 0L)
            return 0.0F;
        scalTime %= this.mTotalTime;
        float distance = 1.0F;
        int motion = this.mMotions.size();
        Iterator iterator = this.mMotions.iterator();
        while (true) {
            motionDistance = distance;
            motionTime = scalTime;
            if (iterator.hasNext()) {
                IMotion iMotion = (IMotion) iterator.next();
                float time = (float) scalTime;
                motionDistance = distance * iMotion.getForwardDistance(time);
                motionTime = (long) (time - iMotion.getTotalTime());
                distance = motionDistance;
                scalTime = motionTime;
                if (motionTime < 0L)
                    break;
                continue;
            }
            break;
        }
        distance = motionDistance;
        if (motionTime > 0L) {
            motion--;
            while (true) {
                distance = motionDistance;
                if (motion >= 0) {
                    IMotion iMotion = mMotions.get(motion);
                    distance = (float) motionTime;
                    motionDistance *= iMotion.getReverseDistance(distance);
                    motionTime = (long) (distance - iMotion.getTotalTime());
                    if (motionTime < 0L)
                        return motionDistance;
                    motion--;
                    continue;
                }
                break;
            }
        }
        return distance;
    }
}
