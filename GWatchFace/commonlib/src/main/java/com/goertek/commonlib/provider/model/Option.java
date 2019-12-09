package com.goertek.commonlib.provider.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Optional;

public class Option {
    @SerializedName("layer")
    private List<Layer> layers;

    @SerializedName("@index")
    private String index;

    @SerializedName("@res_preview")
    private String resPreview;

    @SerializedName("@res_border_preview")
    private String resBorderPreview;

    @SerializedName("@data_type")
    private String dataType;

    public List<Layer> getLayers() {
        return layers;
    }

    public String getIndex() {
        return index;
    }

    public String getResPreview() {
        return resPreview;
    }

    public String getDataType() {
        return dataType;
    }

    public String getResBorderPreview() {
        return resBorderPreview;
    }

    /**
     * 获取指定layer
     * @param i layer索引
     * @return layer对象
     */
    public Optional<Layer> getLayer(String i) {
        if ((layers == null) || (layers.size() <= 0)) {
            return Optional.empty();
        }
        for (Layer layer : layers) {
            if (layer == null) {
                continue;
            }
            if (TextUtils.equals(layer.getIndex().trim(), i.trim())) {
                return Optional.of(layer);
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return " Option { layers=" + layers + " , index=" + index + " , resPreview=" + resPreview + " , dataType="
                       + dataType + " ,resBoredrPreview=" + resBorderPreview + " } ";
    }
}

