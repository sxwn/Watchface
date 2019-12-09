package com.goertek.commonlib.provider.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Optional;

public class Styles {

    @SerializedName("style")
    private List<Style> styles;

    @SerializedName("@selected_style")
    private String selectedStyle;

    @SerializedName("@style_count")
    private String styleCount;

    public List<Style> getStyles() {
        return styles;
    }

    /**
     * 获取指定style
     * @param i style索引
     * @return style对象
     */
    public Style getStyle(String i) {
        if ((styles == null) || (styles.size() <= 0)) {
            return null;
        }
        for (Style style : styles) {
            if (style == null) {
                continue;
            }
            if (TextUtils.equals(style.getIndex().trim(), i.trim())) {
                return style;
            }
        }
        return null;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

    public String getSelectedStyle() {
        return selectedStyle;
    }

    public void setSelectedStyle(String selectedStyle) {
        this.selectedStyle = selectedStyle;
    }

    public String getStyleCount() {
        return styleCount;
    }

    public void setStyleCount(String styleCount) {
        this.styleCount = styleCount;
    }

    @Override
    public String toString() {
        return "Styles{" + "styles=" + styles + ", selectedStyle='" + selectedStyle + '\'' + ", styleCount='"
                       + styleCount + '\'' + '}';
    }
}
