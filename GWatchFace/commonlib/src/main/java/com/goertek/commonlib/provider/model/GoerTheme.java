package com.goertek.commonlib.provider.model;

import com.google.gson.annotations.SerializedName;

public class GoerTheme {
    private static final String TAG = "GoerTheme";

    @SerializedName("author")
    private String author;
    @SerializedName("briefinfo")
    private String briefinfo;
    @SerializedName("designer")
    private String designer;
    @SerializedName("font")
    private String font;
    @SerializedName("font-cn")
    private String fontCn;
    @SerializedName("screen")
    private String screen;
    @SerializedName("title")
    private String title;
    @SerializedName("title-cn")
    private String titleCn;
    @SerializedName("version")
    private String version;

    public String getTitle() {
        return this.title;
    }

    public String getTitleCn() {
        return this.titleCn;
    }

    public String getVersion() {
        return this.version;
    }

    public String toString() {
        return " providers { " + this.title + " , " + this.titleCn + " , " + this.author + " , " + this.designer + " , " + this.screen + " , " + this.version + " , " + this.font + " , " + this.fontCn + " , " + this.briefinfo + " } ";
    }

}
