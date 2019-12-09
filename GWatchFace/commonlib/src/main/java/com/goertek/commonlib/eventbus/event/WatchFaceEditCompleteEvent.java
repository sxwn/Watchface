package com.goertek.commonlib.eventbus.event;

public class WatchFaceEditCompleteEvent {
    private String id;

    public WatchFaceEditCompleteEvent(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }
}
