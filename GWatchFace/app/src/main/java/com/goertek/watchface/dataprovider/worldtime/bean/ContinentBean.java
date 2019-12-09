package com.goertek.watchface.dataprovider.worldtime.bean;

import android.graphics.drawable.Drawable;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class ContinentBean {
    private static final String TAG = "ContinentBean";

    private String continentName;

    private Drawable imageResource;

    public ContinentBean(String continentName, Drawable imageDawable) {
        this.continentName = continentName;
        this.imageResource = imageDawable;
    }

    public String getContinentName() {
        return this.continentName;
    }

    public Drawable getImageResource() {
        return this.imageResource;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public void setImageResource(Drawable drawable) {
        this.imageResource = drawable;
    }
}