package com.goertek.commonlib.provider.manager;

import android.graphics.Bitmap;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.goertek.commonlib.utils.LogUtil;
import com.goertek.commonlib.utils.WatchFaceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * bitmap缓存管理
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/08
 */
public class ResourceCache {
    private static final String TAG = "ResourceCache";
    
    private ResourceResolver mResolver;
    private HashMap<String, Bitmap> mBitmapCache;
    private HashMap<String, Drawable> mDrawableCache;
    private HashMap<String, Movie> mMovieCache;
    private HashMap<String, Typeface> mTypefaceCache;

    ResourceCache(ResourceResolver resourceResolver) {
        mResolver = resourceResolver;
        mBitmapCache = new HashMap<>(0);
        mDrawableCache = new HashMap<>(0);
        mMovieCache = new HashMap<>(0);
        mTypefaceCache = new HashMap<>(0);
    }

    public Bitmap getBitmap(String str) {
        Bitmap bitmap = null;
        if (!TextUtils.isEmpty(str)) {
            bitmap = mBitmapCache.get(str);
            if(bitmap == null){
                bitmap = mResolver.resolveBitmapByName(str);
                if(bitmap != null){
                    mBitmapCache.put(str, bitmap);
                }
            }
        }
        return bitmap;
    }

    public List<Bitmap> getBitmaps(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e(TAG, "getBitmaps(), nameStr is null");
            return Collections.emptyList();
        }
        List<Bitmap> bitmapList = new ArrayList<>(0);
        List<String> bitmapStrList = WatchFaceUtil.getStringValues(str);
        for (String bitmapStr : bitmapStrList) {
            if (bitmapStr.endsWith(".png")) {
                Bitmap bitmap = mBitmapCache.get(bitmapStr);
                if (bitmap == null) {
                    bitmap = mResolver.resolveBitmapByName(bitmapStr);
                    if(bitmap != null){
                        mBitmapCache.put(bitmapStr, bitmap);
                    }
                }
                bitmapList.add(bitmap);
            }
        }
        return bitmapList;
    }

    public Drawable getDrawable(String str) {
        Drawable drawable = null;
        if (!TextUtils.isEmpty(str)) {
            drawable = mDrawableCache.get(str);
            if (drawable == null) {
                drawable = mResolver.resolveDrawableByName(str);
                if(drawable != null){
                    mDrawableCache.put(str, drawable);
                }
            }
        }
        return drawable;
    }

    public List<Drawable> getDrawables(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e(TAG, "getDrawables(), nameStr is null");
            return Collections.emptyList();
        }
        List<Drawable> drawableList = new ArrayList<>(0);
        List<String> drawableStrList = WatchFaceUtil.getStringValues(str);
        for (String drawableStr : drawableStrList) {
            if (drawableStr.endsWith(".png")) {
                Drawable drawable = mDrawableCache.get(drawableStr);
                if (drawable == null) {
                    drawable = mResolver.resolveDrawableByName(drawableStr);
                    if (drawable != null) {
                        mDrawableCache.put(drawableStr, drawable);
                    }
                }
                drawableList.add(drawable);
            }
        }
        return drawableList;
    }

    public Movie getMovie(String str) {
        if (!TextUtils.isEmpty(str)) {
            Movie movie = mMovieCache.get(str);
            if (movie == null) {
                movie = mResolver.resolveGifByName(str);
                if (movie != null) {
                    mMovieCache.put(str, movie);
                }
            }
        }
        return null;
    }

    public Typeface getTypeface(String str) {
        if (!TextUtils.isEmpty(str)) {
            Typeface typeface = mTypefaceCache.get(str);
            if (typeface == null) {
                typeface = mResolver.resolveTypefaceByName(str);
                if (typeface != null) {
                    mTypefaceCache.put(str, typeface);
                }
            }
        }
        return null;
    }

    public void onDestroy() {
        if (!mBitmapCache.isEmpty()) {
            for (Map.Entry<String, Bitmap> entry : mBitmapCache.entrySet()) {
                Bitmap bitmap = entry.getValue();
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            }
            mBitmapCache.clear();
        }
        if (!mDrawableCache.isEmpty()) {
            mDrawableCache.clear();
        }
        if (!mMovieCache.isEmpty()) {
            mMovieCache.clear();
        }
        if (!mTypefaceCache.isEmpty()) {
            mTypefaceCache.clear();
        }
    }
}
