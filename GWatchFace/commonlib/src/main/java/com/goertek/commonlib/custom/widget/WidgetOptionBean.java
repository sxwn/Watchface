package com.goertek.commonlib.custom.widget;

import android.graphics.drawable.Drawable;

/**
 * 功能列表
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/23
 */
public class WidgetOptionBean {
    private static final String TAG = "WidgetOptionBean";

    private String content;

    private Drawable icon;

    private boolean isSelected;

    private String optionIndex;

    private int type;

    public WidgetOptionBean() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getOptionIndex() {
        return optionIndex;
    }

    public void setOptionIndex(String optionIndex) {
        this.optionIndex = optionIndex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WidgetOptionBean{" +
                       "content='" + content + '\'' +
                       ", icon=" + icon +
                       ", isSelected=" + isSelected +
                       ", optionIndex='" + optionIndex + '\'' +
                       ", type=" + type +
                       '}';
    }
}
