package com.goertek.commonlib.provider.model;


import android.text.TextUtils;

import com.goertek.commonlib.utils.WatchFaceUtil;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Optional;

public class Element {
    @SerializedName("option")
    private List<Option> options;

    @SerializedName("container")
    private List<Container> containers;

    @SerializedName("layer")
    private List<Layer> layers;

    @SerializedName("@label")
    private String label;

    @SerializedName("@is_support_option")
    private String isSupportOption;

    @SerializedName("@selected_option")
    private String selectedOption;

    @SerializedName("@res_preview")
    private String resPreview;

    public List<Option> getOptions() {
        return options;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public String getLabel() {
        return label;
    }

    public String getIsSupportOption() {
        return isSupportOption;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String index) {
        selectedOption = index;
    }

    public String getResPreview() {
        return resPreview;
    }

    /**
     * 获取指定option
     * @param optionIndexStr option索引
     * @return option对象
     */
    public Option getOption(String optionIndexStr) {
        // 修复样式中选择无的时候崩溃的bug
        if (TextUtils.isEmpty(optionIndexStr)) {
            return null;
        }
        if ((options == null) || (options.size() <= 0)) {
            return null;
        }
        for (Option option : options) {
            if (option == null) {
                continue;
            }
            if (TextUtils.equals(option.getIndex().trim(), optionIndexStr.trim())) {
                return option;
            }
        }
        return null;
    }

    /**
     * 获取指定container
     * @param i container索引
     * @return container对象
     */
    public Container getContainer(String i) {
        if ((containers == null) || (containers.size() <= 0)) {
            return null;
        }
        for (Container container : containers) {
            if (container == null) {
                continue;
            }
            if (TextUtils.equals(container.getIndex().trim(), i.trim())) {
                return container;
            }
        }
        return null;
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

    /**
     * 获取预览图
     * @return 预览图名称
     */
    public String getPreview() {
        if (WatchFaceUtil.getBoolValue(getIsSupportOption())) {
            Option option = getOption(getSelectedOption());
            if (option == null) {
                return "";
            }
            return option.getResPreview();
        } else {
            return getResPreview();
        }
    }

    /**
     * 获取预览图
     * @param i container索引
     * @return 预览图名称
     */
    public String getPreview(String i) {
        Container container = getContainer(i);
        if (container == null) {
            return "";
        }
        if (WatchFaceUtil.getBoolValue(container.getIsSupportOption())) {
            Option option = getOption(container.getSelectedOption());
            if (option == null) {
                return "";
            }
            return option.getResPreview();
        } else {
            return container.getResPreview();
        }
    }

    /**
     * 获取蓝色异形预览图
     *
     * @param i container索引
     * @return 蓝色异形预览图名称
     */
    public String getBorderPreview(String i) {
        Container container = getContainer(i);
        if (container == null) {
            return "";
        }
        if (WatchFaceUtil.getBoolValue(container.getIsSupportOption())) {
            Option option = getOption(container.getSelectedOption());
            if (option == null) {
                return "";
            }
            return option.getResBorderPreview();
        } else {
            return "";
        }
    }

    /**
     * 获取蓝色异形预览图(健康三环)
     *
     * @param i container索引
     * @return 蓝色异形预览图名称
     */
    public String getContainerPreview(String i) {
        Container container = getContainer(i);
        if (container == null) {
            return "";
        }
        if (WatchFaceUtil.getBoolValue(container.getIsSupportOption())) {
            return container.getResRotatePreview();
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        return "Element{" +
                "options=" + options +
                ", containers=" + containers +
                ", layers=" + layers +
                ", label='" + label + '\'' +
                ", isSupportOption='" + isSupportOption + '\'' +
                ", selectedOption='" + selectedOption + '\'' +
                ", resPreview='" + resPreview + '\'' +
                '}';
    }
}