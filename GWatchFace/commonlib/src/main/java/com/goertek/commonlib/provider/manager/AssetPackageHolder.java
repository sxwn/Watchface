package com.goertek.commonlib.provider.manager;

public class AssetPackageHolder {
    private static final String TAG = "AssetPackageHolder";

    private static AssetPackageHolder instance;
    private AssetPackage assetPackage;

    public static AssetPackageHolder getInstance() {
        if (instance == null) {
            synchronized (AssetPackageHolder.class){
                if (instance == null){
                    instance = new AssetPackageHolder();
                }
            }
        }
        return instance;
    }

    public static void setInstance(AssetPackageHolder assetPackageHolder) {
        instance = assetPackageHolder;
    }

    public AssetPackage getAssetPackage() {
        return assetPackage;
    }

    public void setAssetPackage(AssetPackage assetPackage) {
        this.assetPackage = assetPackage;
    }
}
