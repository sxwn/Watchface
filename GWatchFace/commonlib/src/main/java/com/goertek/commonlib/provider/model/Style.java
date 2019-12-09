package com.goertek.commonlib.provider.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Style {

    @SerializedName("@index")
    private String index;

    @SerializedName("@background_available_option")
    private String backgroundAvailableOption;

    @SerializedName("@background_selected_option")
    private String backgroundSelectedOption;

    @SerializedName("@color_available_option")
    private String colorAvailableOption;

    @SerializedName("@color_selected_option")
    private String colorSelectedOption;

    @SerializedName("@dial_available_option")
    private String dialAvailableOption;

    @SerializedName("@dial_selected_option")
    private String dialSelectedOption;

    @SerializedName("@date_available_option")
    private String dateAvailableOption;

    @SerializedName("@date_selected_option")
    private String dateSelectedOption;

    @SerializedName("@time_available_option")
    private String timeAvailableOption;

    @SerializedName("@time_selected_option")
    private String timeSelectedOption;

    @SerializedName("@widget_available_containers")
    private String widgetAvailableContainers;

    @SerializedName("@container_selected_options")
    private String containerSelectedOptions;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getBackgroundSelectedOption() {
        return backgroundSelectedOption;
    }

    public void setBackgroundSelectedOption(String backgroundSelectedOption) {
        this.backgroundSelectedOption = backgroundSelectedOption;
    }

    public String getColorSelectedOption() {
        return colorSelectedOption;
    }

    public void setColorSelectedOption(String colorSelectedOption) {
        this.colorSelectedOption = colorSelectedOption;
    }

    public String getDialSelectedOption() {
        return dialSelectedOption;
    }

    public void setDialSelectedOption(String dialSelectedOption) {
        this.dialSelectedOption = dialSelectedOption;
    }

    public String getDateSelectedOption() {
        return dateSelectedOption;
    }

    public void setDateSelectedOption(String dateSelectedOption) {
        this.dateSelectedOption = dateSelectedOption;
    }

    public String getTimeSelectedOption() {
        return timeSelectedOption;
    }

    public void setTimeSelectedOption(String timeSelectedOption) {
        this.timeSelectedOption = timeSelectedOption;
    }

    public String getWidgetAvailableContainers() {
        return widgetAvailableContainers;
    }

    public void setWidgetAvailableContainers(String widgetAvailableContainers) {
        this.widgetAvailableContainers = widgetAvailableContainers;
    }

    public String getContainerSelectedOptions() {
        return containerSelectedOptions;
    }

    public void setContainerSelectedOptions(String containerSelectedOptions) {
        this.containerSelectedOptions = containerSelectedOptions;
    }

    public String getBackgroundAvailableOption() {
        return backgroundAvailableOption;
    }

    public String getDialAvailableOption() {
        return dialAvailableOption;
    }

    public String getColorAvailableOption() {
        return colorAvailableOption;
    }

    public String getDateAvailableOption() {
        return dateAvailableOption;
    }

    public String getTimeAvailableOption() {
        return timeAvailableOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Style style = (Style) o;
        return Objects.equals(index, style.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }

    @Override
    public String toString() {
        return "Style{" + "index='" + index + '\'' + ", backgroundSelectedOption='" + backgroundSelectedOption + '\''
                       + ", colorSelectedOption='" + colorSelectedOption + '\'' + ", dialSelectedOption='" + dialSelectedOption
                       + '\'' + ", dateSelectedOption='" + dateSelectedOption + '\'' + ", timeSelectedOption='"
                       + timeSelectedOption + '\'' + ", widgetAvailableContainers='" + widgetAvailableContainers + '\''
                       + ", containerSelectedOptions='" + containerSelectedOptions + '\'' + '}';
    }
}
