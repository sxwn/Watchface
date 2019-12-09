package com.goertek.commonlib.eventbus.event;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class FragmentScrollEvent {
    private static final String TAG = "FragmentScrollEvent";

    private String fragmentTag;

    private int itemIndex;

    public FragmentScrollEvent(int index, String tag) {
        this.itemIndex = index;
        this.fragmentTag = tag;
    }

    public String getFragmentTag() {
        return this.fragmentTag;
    }

    public int getItemIndex() {
        return this.itemIndex;
    }

    public void setFragmentTag(String tag) {
        this.fragmentTag = tag;
    }

    public void setItemIndex(int index) {
        this.itemIndex = index;
    }
}
