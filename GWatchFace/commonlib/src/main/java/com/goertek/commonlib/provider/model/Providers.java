package com.goertek.commonlib.provider.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Providers {
    @SerializedName("provider")
    private List<Provider> providers;

    public List<Provider> getProviders() {
        return providers;
    }

    @Override
    public String toString() {
        return " providers { providers=" + providers + " } ";
    }
}
