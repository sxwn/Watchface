package com.goertek.watchface.dataprovider.sportshealth;

import android.nfc.Tag;
import android.text.TextUtils;

import com.goertek.commonlib.provider.data.manager.IDataProvider;
import com.goertek.commonlib.provider.data.model.FloatRangeValue;
import com.goertek.commonlib.provider.data.model.IntRangeValue;
import com.goertek.commonlib.provider.data.util.DataConstantUtils;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.watchface.dataprovider.sportshealth.controller.SportsHealthController;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class SportsHealthProvider implements IDataProvider {
    private static final String TAG = "SportsHealthProvider";

    private static final int LAYER_INDEX_DEFAULT = 0;

    private static final int LAYER_INDEX_ONE = 1;

    private SportsHealthController mSportsHealthController = SportsHealthController.getInstance();

    public SportsHealthProvider() {
    }

    private int getLayerIndex(String index) {
        int temp;
        try {
            temp = Integer.parseInt(index);
        } catch (NumberFormatException var3) {
            temp = 0;
        }
        LogUtil.e(TAG, "temp========= " + temp);
        return temp == 0 ? 0 : 1;
    }

    public void doClick(String var1) {
        /*byte var2;
        label43: {
            switch(var1.hashCode()) {
                case -1758965441:
                    if (var1.equals("climb_stair")) {
                        var2 = 5;
                        break label43;
                    }
                    break;
                case -1036497644:
                    if (var1.equals("activity_hour")) {
                        var2 = 4;
                        break label43;
                    }
                    break;
                case -856922688:
                    if (var1.equals("middle_high_intensity")) {
                        var2 = 2;
                        break label43;
                    }
                    break;
                case -349014195:
                    if (var1.equals("data_health_sporttime_current")) {
                        var2 = 7;
                        break label43;
                    }
                    break;
                case -168965370:
                    if (var1.equals("calories")) {
                        var2 = 3;
                        break label43;
                    }
                    break;
                case 3540684:
                    if (var1.equals("step")) {
                        var2 = 1;
                        break label43;
                    }
                    break;
                case 28515718:
                    if (var1.equals("max_oxygen_uptake")) {
                        var2 = 8;
                        break label43;
                    }
                    break;
                case 288459765:
                    if (var1.equals("distance")) {
                        var2 = 6;
                        break label43;
                    }
                    break;
                case 1930449209:
                    if (var1.equals("heart_rate")) {
                        var2 = 0;
                        break label43;
                    }
            }

            var2 = -1;
        }

        switch(var2) {
            case 0:
                this.mSportsHealthController.gotoHealthApp("com.huawei.bone.view.heartrate.activity.HeartRateMainActivity");
                return;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                this.mSportsHealthController.gotoHealthApp("com.huawei.bone.view.dailytrack.activity.DailyTrackMainActivity");
                return;
            default:
        }*/
    }

    public FloatRangeValue getFloatRangeValue(String valueType) {
        return new FloatRangeValue(0.0F, 0.0F, 0.0F);
    }

    public float getFloatValue(String varvalueType) {
        return 0.0F;
    }

    public int getIndexValue(String valueType) {
        return !TextUtils.isEmpty(valueType) && TextUtils.equals(valueType, "data_current_heat_rate_step") ? this.mSportsHealthController.getCurrentHeatRateStep() : 0;
    }

    public IntRangeValue getIntRangeValue(String valueType) {

        switch (valueType) {
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_COLORIE_MAX_MIN_VALUE:
                return this.mSportsHealthController.getCalorieRange();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_SPORTTIME_MAX_MIN_VALUE:
                return this.mSportsHealthController.getSportTimeRange();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_HEAT_MAX_MIN_VALUE:
                return this.mSportsHealthController.getHeatRange();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_STAND_MAX_MIN_VALUE:
                return this.mSportsHealthController.getStandRange();
            case DataConstantUtils.VALUE_TYPE_DATA_CLIM:
                return this.mSportsHealthController.getHealthClimRange();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_STEP_TARGET_MAX_MIN_VALUE:
                return this.mSportsHealthController.getStepRange();
            default:
                return new IntRangeValue(50, 100, 0);
        }
    }

    public int getIntValue(String valueType) {
        switch (valueType) {
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_CALORIE_CURRENT:
                return this.mSportsHealthController.getHealthCalorieCurrentInt();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_SPORTTIME_CURRENT:
                return this.mSportsHealthController.getHealthSportTimeCurrentInt();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_STEP_CURRENT:
                return this.mSportsHealthController.getHealthStepCurrentInt();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_HEARTRATE_CURRENT:
                return this.mSportsHealthController.getHealthHeartRateCurrentInt();
            case DataConstantUtils.VALUE_TYPE_DATA_CLIM:
                return this.mSportsHealthController.getHealthClimCurrentInt();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_STAND_CURRENT:
                return this.mSportsHealthController.getHealthStandCurrentInt();
            default:
                return 0;
        }

    }

    public int getLayerIndexValue(String valueType) {

        switch (valueType) {
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_STEP_CURRENT_INDEX:
                return this.getLayerIndex(this.mSportsHealthController.getHealthStepCurrent());
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_CALORIE_CURRENT_INDEX:
                return this.getLayerIndex(this.mSportsHealthController.getHealthCalorieCurrent());
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_HEARTRATE_CURRENT_INDEX:
                return this.getLayerIndex(this.mSportsHealthController.getHealthHeartRateCurrent());
            default:
                return 1;
        }
    }

    public String getStringValue(String valueType) {
        LogUtil.e(TAG, "VALUE_TYPE_DATA_HEALTH_STEP_CURRENT ============" + valueType);
        switch (valueType) {
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_CALORIE_CURRENT:
                return this.mSportsHealthController.getHealthCalorieCurrent();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_CALORIE_MAX:
                return this.mSportsHealthController.getHealthHeartRateMax();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_HEARTRATE_MIN:
                return this.mSportsHealthController.getHealthHeartRateMin();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_SPORTTIME_CURRENT:
                return this.mSportsHealthController.getHealthSportTimeCurrent();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_STAND:
                return this.mSportsHealthController.getStandNumber();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_SPORTSTIME:
                return this.mSportsHealthController.getHealthSportTime();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_STEP_CURRENT:
                return this.mSportsHealthController.getHealthStepCurrent();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_HEARTRATE_CURRENT:
                return this.mSportsHealthController.getHealthHeartRateCurrent();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_STEP_CURRENT_WITH_UNIT:
                return this.mSportsHealthController.getHealthStepCurrentWithUnit();
            case DataConstantUtils.VALUE_TYPE_DATA_CLIM:
                return this.mSportsHealthController.getHealthClimCurrent();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_STEP:
                return this.mSportsHealthController.getHealthStep();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_STAND_CURRENT:
                return this.mSportsHealthController.getHealthStandCurrent();
            case DataConstantUtils.VALUE_TYPE_DATA_HEALTH_CALORIE_CURRENT_WITH_UNIT:
                return this.mSportsHealthController.getHealthCalorieCurrentWithUnit();
            default:
                return "";
        }
    }

    public String getStringValue(String valueType, String... format) {
        return "";
    }
}