package com.goertek.watchface.dataprovider.worldtime.bean;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class TimeZoneBean {
    private String cityCode;
    private String cityName;
    private String gmtOffSet;

    public TimeZoneBean(String cityName, String cityCode, String gmtOffSet) {
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.gmtOffSet = gmtOffSet;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public String getCityName() {
        return this.cityName;
    }

    public String getGmtOffSet() {
        return this.gmtOffSet;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setGmtOffSet(String gmtOffSet) {
        this.gmtOffSet = gmtOffSet;
    }
}