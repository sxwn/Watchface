package com.goertek.commonlib.provider.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Provider {
    @SerializedName("@dpi")
    private String dpi;

    @SerializedName("element")
    private List<Element> elements;

    @SerializedName("styles")
    private Styles styles;

    @SerializedName("config")
    private Config configs;

    public List<Element> getElements() {
        return elements;
    }

    public Styles getStyles() {
        return styles;
    }

    public Config getConfigs() {
        return configs;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return " provider { dpi=" + dpi + " , elements=" + elements + " } ";
    }
}

