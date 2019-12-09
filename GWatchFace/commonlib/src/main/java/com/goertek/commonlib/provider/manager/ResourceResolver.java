package com.goertek.commonlib.provider.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import com.goertek.commonlib.provider.model.GoerTheme;
import com.goertek.commonlib.provider.model.Providers;
import com.goertek.commonlib.utils.FileUtil;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.commonlib.utils.WatchFaceUtil;
import com.goertek.commonlib.xml.GsonXmlBuilder;
import com.goertek.commonlib.xml.GsonXmler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ResourceResolver {
    private static final String TAG = ResourceResolver.class.getSimpleName();

    private static final String CONFIG_FILE_NAME = "watch_face_config.xml";
    private static final String FONTS_CACHE_DIR_NAME = "font_cache";
    private static final String FONTS_DIR_NAME = "fonts";
    private static final String INFO_FILE_NAME = "watch_face_info.xml";
    private static final String VIDEO_CACHE_DIR_NAME = "video_cache";

    private String mAssetPath;
    private Context mContext;
    private boolean mIsAssets;
    private String mAssetName;
    private String mResDirName;


    public ResourceResolver(Context context, String assetPath, boolean isAssets) {
        mContext = context;
        mAssetPath = assetPath;
        mIsAssets = isAssets;
        mAssetName = getAssetName(mAssetPath);
        mResDirName = Integer.toString(WatchFaceUtil.getDisplayMetrics(mContext));
    }

    /**
     * 获取配置文件名称
     *
     * @param mAssetPath 路径
     */
    private String getAssetName(String mAssetPath) {

        if (mAssetPath == null) {
            return "";
        }
        List<String> asList = WatchFaceUtil.getStringValues(mAssetPath, "/");
        if (asList.size() <= 0) {
            return "";
        } else if (mIsAssets || asList.size() <= 2) {
            return asList.get(asList.size() - 1);
        } else {
            return asList.get(asList.size() - 2);
        }
    }

    /**
     * 通过文件名获取 bitmap对象
     *
     * @param str 文件名
     * @return bitmap对象
     */
    public Bitmap resolveBitmapByName(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e(TAG, "resolveBitmapByName(), name is null");
            return null;
        }
        InputStream inputStream = getInputStreamFromResource(mResDirName, str);

        if (inputStream == null) {
            LogUtil.e(TAG, "this name is error :" + str);
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        try {
            inputStream.close();
        } catch (IOException e) {
            LogUtil.e(TAG, "resolveBitmapByName(), inputStream close exception");
        }
        return bitmap;
    }

    /**
     * 根据名称获取gif对象
     *
     * @param str str
     * @return Movie
     */
    public Movie resolveGifByName(String str) {
        if (TextUtils.isEmpty(str) || !str.endsWith(".gif")) {
            LogUtil.e(TAG, "resolveGifByName(), name is null or not end with .gif");
            return null;
        }
        InputStream inputStream = getInputStreamFromResource(mResDirName, str);
        if (inputStream == null) {
            LogUtil.e(TAG, "resolveGifByName(), inputStream is null of name: " + str);
            return null;
        }
        Movie decodeStream = Movie.decodeStream(inputStream);
        try {
            inputStream.close();
        } catch (IOException unused) {
            LogUtil.e(TAG, "resolveGifByName(), inputStream close exception");
        }
        return decodeStream;
    }


    /**
     * 根据文件资源路径、名称 获取对应的流
     *
     * @param resDirName 文件资源路径
     * @param str        文件名称
     * @return 流对象
     */
    private InputStream getInputStreamFromResource(String resDirName, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e(TAG, "filename is null");
            return null;
        }

        if (!TextUtils.isEmpty(resDirName)) {
            str = resDirName + File.separator + str;
        }

        try {
            if (mIsAssets) {
                return mContext.getAssets().open(mAssetPath + File.separator + str);
            } else {
                return (new ZipFile(mAssetPath)).getInputStream(new ZipEntry(str));
            }
        } catch (IOException e) {
            LogUtil.e(TAG, e.getMessage());
        }
        return null;
    }

    /**
     * 根据文件和名称获取缓存文件的输入流
     *
     * @param file file
     * @param name name
     * @return InputStream
     */
    private InputStream getInputStreamFromCache(File file, String name) {
        InputStream inputStream = null;
        if (file == null || !file.exists() ||
                !file.isDirectory() || TextUtils.isEmpty(name)) {
            return null;
        }

        File cacheFile = new File(file, name);
        if (!cacheFile.exists() || !cacheFile.isFile()) {
            return null;
        }
        try {
            inputStream = new FileInputStream(cacheFile);
        } catch (FileNotFoundException e) {
            LogUtil.e(TAG, "getInputStream() exception : " + e.getMessage());
        }
        return inputStream;
    }


    public String getAssetPath() {
        return mAssetPath;
    }

    /**
     * 通过文件名获取 Drawable对象
     *
     * @param drawableName 文件名
     * @return Drawable对象
     */
    public Drawable resolveDrawableByName(String drawableName) {
        if (!TextUtils.isEmpty(drawableName)) {
            InputStream inputStream = getInputStreamFromResource(mResDirName, drawableName);
            if (inputStream == null) {
                LogUtil.e(TAG, "resolveDrawableByName(), inputStream is null of name:  ");
                return null;
            }
            Drawable drawable = Drawable.createFromResourceStream(mContext.getResources(), null, inputStream, drawableName);
            try {
                inputStream.close();
            } catch (IOException e) {
                LogUtil.e(TAG, "resolveDrawableByName(), inputStream close exception");
            }
            return drawable;
        }
        LogUtil.e(TAG, "resolveDrawableByName(), name is null");
        return null;
    }

    /**
     * 根据字体名称获取对应的字体资源
     *
     * @param typeFaceName typeFaceName
     * @return Typeface
     */
    public Typeface resolveTypefaceByName(String typeFaceName) {
        if (TextUtils.isEmpty(typeFaceName)) {
            LogUtil.e(TAG, "resolveTypefaceByName(), name is null");
            return null;
        }
        String path = mContext.getCacheDir() + File.separator + FONTS_CACHE_DIR_NAME;
        File file = new File(path);
        if (!file.exists() && !file.mkdirs()) {
            LogUtil.e(TAG, "resolveTypefaceByName(), fail to create cache dir");
            return null;
        }
        File typeFaceFile = new File(file, typeFaceName);
        if (!typeFaceFile.exists()) {
            LogUtil.e(TAG, "typeFaceFile not exists");
            InputStream inputStream = getInputStreamFromFontResource(FONTS_DIR_NAME, typeFaceName);
            if (inputStream == null || !FileUtil.writeToFile(inputStream, typeFaceFile)) {
                return null;
            }
        }
        return Typeface.createFromFile(typeFaceFile);
    }

    /**
     * 根据字体路径和名称获取对应的输入流
     *
     * @param fontResPath  fontResPath
     * @param typeFaceName typeFaceName
     * @return InputStream
     */
    private InputStream getInputStreamFromFontResource(String fontResPath, String typeFaceName) {
        if (TextUtils.isEmpty(typeFaceName)) {
            LogUtil.e(TAG, "getInputStreamFromFontResource filename is null");
            return null;
        }
        if (!TextUtils.isEmpty(fontResPath)) {
            typeFaceName = fontResPath + File.separator + typeFaceName;
        }

        try {
            return mContext.getAssets().open(typeFaceName);
        } catch (IOException e) {
            LogUtil.e(TAG, e.getMessage());
            try {
                return (new ZipFile(mAssetPath)).getInputStream(new ZipEntry(typeFaceName));
            } catch (IOException exception) {
                LogUtil.e(TAG, exception.toString());
            }
        }

        return null;
    }

    /**
     * 解析配置文件获取对应的对象
     *
     * @return Providers
     */
    public Providers parserConfigFile() {
        File file = new File(getConfigFileCacheDir());
        if (!file.exists() && !file.mkdirs()) {
            return null;
        }
        File configFile = new File(file, CONFIG_FILE_NAME);
        if (!configFile.exists()) {
            InputStream inputStream = getInputStreamFromResource("", CONFIG_FILE_NAME);
            if (inputStream == null || !FileUtil.writeToFile(inputStream, configFile)) {
                return null;
            }
        }
        return parserXml(getInputStreamFromCache(file, CONFIG_FILE_NAME), Providers.class);
    }

    /**
     * 获取缓存的配置文件的路径
     *
     * @return String
     */
    private String getConfigFileCacheDir() {
        return mContext.getDataDir() + File.separator + mAssetName;
    }

    /**
     * 根据输入流和需要解析成的对象进行解析获取对象
     *
     * @param inputStream inputStream
     * @param cls         cls
     * @return T
     */
    private <T> T parserXml(InputStream inputStream, Class<T> cls) {
        GsonXmler gsonXmler = new GsonXmlBuilder()
                .setSameNameLists(true)
                .create();

        if (inputStream != null) {
            return gsonXmler.fromXml(new InputStreamReader(inputStream), cls);
        }
        return null;
    }

    /**
     * 写入缓存文件
     *
     * @param providers providers
     * @return boolean
     */
    public boolean writeBackConfigFile(Providers providers) {
        boolean result = false;
        FileWriter writer = null;
        try {
            File cacheFile = new File(getConfigFileCacheDir(), CONFIG_FILE_NAME);
            GsonXmler gsonXmler = new GsonXmlBuilder().setSameNameLists(true).create();
            writer = new FileWriter(cacheFile);
            gsonXmler.toXml(writer, providers);
            result = true;
        } catch (IOException e) {
            Log.i(TAG, e.getMessage());
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                Log.i(TAG, "close error." + e.getMessage());
            }
        }
        return result;
    }


    public GoerTheme parserInfoFile() {
        return parserXml(getInputStreamFromResource("", INFO_FILE_NAME), GoerTheme.class);
    }

    public String getVideoPath(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e(TAG, "getVideoPath(), name is null");
            return null;
        }
        File file = new File(mContext.getCacheDir() + File.separator + VIDEO_CACHE_DIR_NAME);
        if (file.exists() || file.mkdirs()) {
            File goalFile = new File(file, str);
            if (goalFile.exists()) {
                return goalFile.getAbsolutePath();
            } else {
                InputStream inputStream = getInputStreamFromResource(mResDirName, str);
                if (inputStream == null || !FileUtil.writeToFile(inputStream, goalFile)) {
                    return null;
                }
            }
        }
        LogUtil.e(TAG, "getVideoPath(), fail to create save dir");
        return null;
    }

}
