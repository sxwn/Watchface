package com.goertek.commonlib.eventbus.event;

public class WatchFaceModuleChangeEvent {
    private String containerIndex;
    private boolean isHaveWidget;
    private String watchFaceAssetPath;

    public WatchFaceModuleChangeEvent(){

    }

    public WatchFaceModuleChangeEvent(String containerIndex) {
        this.containerIndex = containerIndex;
    }

    public WatchFaceModuleChangeEvent(String containerIndex, boolean isHaveWidget, String watchFaceAssetPath) {
        this(containerIndex);
        this.isHaveWidget = isHaveWidget;
        this.watchFaceAssetPath = watchFaceAssetPath;
    }

    public String getContainerIndex() {
        return containerIndex;
    }

    public boolean isHaveWidget() {
        return isHaveWidget;
    }

    public String getWatchFaceAssetPath() {
        return watchFaceAssetPath;
    }

}
