package com.goertek.commonlib.provider.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Optional;

public class Container {
    @SerializedName("layer")
    private List<Layer> layers;

    @SerializedName("@index")
    private String index;

    @SerializedName("@rect")
    private String rect;

    @SerializedName("@is_support_option")
    private String isSupportOption;

    @SerializedName("@res_preview")
    private String resPreview;

    @SerializedName("@selected_option")
    private String selectedOption;

    @SerializedName("@available_option")
    private String availableOption;

    @SerializedName("@data_type")
    private String dataType;

    @SerializedName("@is_available")
    private String isAvailable;

    @SerializedName("@rotate_degree")
    private String rotateDegree;

    @SerializedName("@center_degree")
    private String centerDegree;

    @SerializedName("@res_rotate_preview")
    private String resRotatePreview;

    @SerializedName("@res_radian")
    private String resRadian;

    @SerializedName("@is_arc_linear")
    private String isArcLinear;

    public List<Layer> getLayers() {
        return layers;
    }

    public String getIndex() {
        return index;
    }

    public String getRect() {
        return rect;
    }

    public String getIsSupportOption() {
        return isSupportOption;
    }

    public String getResPreview() {
        return resPreview;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public String getAvailableOption() {
        return availableOption;
    }

    public String getDataType() {
        return dataType;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public String getRotateDegree() {
        return rotateDegree;
    }

    public void setRotateDegree(String rotateDegree) {
        this.rotateDegree = rotateDegree;
    }

    public String getResRotatePreview() {
        return resRotatePreview;
    }

    public void setResRotatePreview(String resRotatePreview) {
        this.resRotatePreview = resRotatePreview;
    }

    public String getCenterDegree() {
        return centerDegree;
    }

    public void setCenterDegree(String centerDegree) {
        this.centerDegree = centerDegree;
    }

    public String getResRadian() {
        return resRadian;
    }

    public void setResRadian(String resRadian) {
        this.resRadian = resRadian;
    }

    public String getIsArcLinear() {
        return isArcLinear;
    }

    public void setIsArcLinear(String isArcLinear) {
        this.isArcLinear = isArcLinear;
    }

    /**
     * 获取指定layer
     * @param i layer索引
     * @return layer对象
     */
    public Layer getLayer(String i) {
        if ((layers == null) || (layers.size() <= 0)) {
            return null;
        }
        for (Layer layer : layers) {
            if (layer == null) {
                continue;
            }
            if (TextUtils.equals(layer.getIndex().trim(), i.trim())) {
                return layer;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return " Container { layers=" + layers + " , index=" + index + " , rect=" + rect + " , isSupportOption="
                       + isSupportOption + " , selectedOption=" + selectedOption + " , availableOption=" + availableOption
                       + " , dataType=" + dataType + " } ";
    }
}

