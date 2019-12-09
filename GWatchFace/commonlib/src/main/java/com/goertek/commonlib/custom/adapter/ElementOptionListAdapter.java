package com.goertek.commonlib.custom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goertek.commonlib.R;
import com.goertek.commonlib.custom.common.BlueCircleView;
import com.goertek.commonlib.custom.common.RandomBackgroundView;
import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Container;
import com.goertek.commonlib.provider.model.Element;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.provider.model.Option;
import com.goertek.commonlib.provider.model.Style;
import com.goertek.commonlib.provider.model.Styles;
import com.goertek.commonlib.utils.CommonConstantsUtil;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.commonlib.utils.WatchFaceUtil;
import com.goertek.commonlib.view.unit.UnitConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 表盘style列表界面适配器
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/22
 */
public class ElementOptionListAdapter extends RecyclerView.Adapter {
    private static final String TAG = "ElementOptionListAdapter";

    private static final String SPLIT_COMMA = ",";

    private Context mContext;

    private AssetPackage mAssetPackage;

    private String mLabel;

    private List<ItemView> mItemViews;

    private List<String> mOptionIndexList;

    private List<String> mContainerIndexList;

    private List<String> mStyleIndexList;

    private Drawable mWidgetMaskBg;

    private Bitmap mArcBackgroundPreview;

    /**
     * 构造方法
     *
     * @param context      上下文对象
     * @param label        列表对应的元素标签
     * @param assetPackage 资源包对象
     * @param blueBorder   蓝色背景圈
     */
    public ElementOptionListAdapter(Context context, String label, AssetPackage assetPackage,
                                    RelativeLayout blueBorder) {
        mContext = context;
        mAssetPackage = assetPackage;
        mLabel = label;
        mItemViews = new ArrayList<>(0);
        mWidgetMaskBg = context.getResources().getDrawable(R.drawable.clock_widget_mask_bg);
        showBlueBg(label, blueBorder);
        if (TextUtils.equals(mLabel, UnitConstants.STYLES)) {
            initStyleIndexList();
            initContainerIndexList();
            return;
        } else if (TextUtils.equals(mLabel, UnitConstants.LABEL_WIDGET)) {
            initContainerIndexList();
            return;
        } else {
            initContainerIndexList();
            initOptionIndexList();
        }
    }


    private void updateElementDataBySelectedStyle() {
        Styles styles = mAssetPackage.getElementProvider().getStyles();
        if (styles == null) {
            return;
        }
        Style style = styles.getStyle(styles.getSelectedStyle());
        if (style == null) {
            return;
        }
        if (!TextUtils.isEmpty(style.getBackgroundSelectedOption())) {
            Element element = mAssetPackage.getElementProvider().getElementWithLabel(UnitConstants.LABEL_BACKGROUND);
            if (element != null) {
                element.setSelectedOption(style.getBackgroundSelectedOption());
            }
        }

        if (!TextUtils.isEmpty(style.getDateSelectedOption())) {
            Element element = mAssetPackage.getElementProvider().getElementWithLabel(UnitConstants.LABEL_DATE);
            if (element != null) {
                element.setSelectedOption(style.getDateSelectedOption());
            }
        }
        if (!TextUtils.isEmpty(style.getTimeSelectedOption())) {
            Element element = mAssetPackage.getElementProvider().getElementWithLabel(UnitConstants.LABEL_TIME);
            if (element != null) {
                element.setSelectedOption(style.getTimeSelectedOption());
            }
        }
        if (!TextUtils.isEmpty(style.getDialSelectedOption())) {
            Element element = mAssetPackage.getElementProvider().getElementWithLabel(UnitConstants.LABEL_DIAL);
            if (element != null) {
                element.setSelectedOption(style.getDialSelectedOption());
            }
        }
        updateWidgetDataBySelectedStyle();
    }

    /**
     * 根据选择的style更新widget的数据
     */
    private void updateWidgetDataBySelectedStyle() {
        Styles styles = mAssetPackage.getElementProvider().getStyles();
        if (styles == null) {
            return;
        }
        Style style = styles.getStyle(styles.getSelectedStyle());
        if (style == null) {
            return;
        }
        Element element = mAssetPackage.getElementProvider().getElementWithLabel(UnitConstants.LABEL_WIDGET);
        if (element == null) {
            return;
        }
        List<Container> containers = element.getContainers();
        if ((containers == null) || (containers.size() == 0)) {
            return;
        }
        if (!TextUtils.isEmpty(style.getWidgetAvailableContainers())) {
            List<String> availableContainers =
                    Arrays.asList(style.getWidgetAvailableContainers().split(SPLIT_COMMA));
            List<String> containerSelects = Arrays.asList(style.getContainerSelectedOptions().split(SPLIT_COMMA));
            if (availableContainers.size() != containerSelects.size()) {
                return;
            }
            int i = 0;
            for (Container container : containers) {
                if (availableContainers.contains(container.getIndex())) {
                    container.setIsAvailable(String.valueOf(true));
                    container.setSelectedOption(containerSelects.get(i));
                    i++;
                } else {
                    container.setIsAvailable(String.valueOf(false));
                }
            }
        } else {
            for (Container container : containers) {
                container.setIsAvailable(String.valueOf(false));
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemView itemView = new ItemView(mContext);
        mItemViews.add(itemView);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.itemView instanceof ItemView) {
            ItemView itemView = (ItemView) (holder.itemView);
            updateItemView(itemView, position);
        }
    }

    @Override
    public int getItemCount() {
        if (TextUtils.equals(mLabel, UnitConstants.LABEL_WIDGET)) {
            return 1;
        } else if (TextUtils.equals(mLabel, UnitConstants.STYLES)) {
            return mStyleIndexList.size();
        } else {
            return mOptionIndexList.size();
        }
    }

    /**
     * 更新选择的option
     *
     * @param position 位置
     */
    public void updateSelectedOption(int position) {
        if (TextUtils.equals(mLabel, UnitConstants.LABEL_WIDGET)) {
            return;
        }
        if (TextUtils.equals(mLabel, UnitConstants.STYLES)) {
            String index = mStyleIndexList.get(position);
            Styles styles = mAssetPackage.getElementProvider().getStyles();
            if (styles != null) {
                styles.setSelectedStyle(index);
                // 根据选择的style更新element的配置
                updateElementDataBySelectedStyle();
            }
        } else {
            String index = mOptionIndexList.get(position);
            Element element = mAssetPackage.getElementProvider().getElementWithLabel(mLabel);
            if (element != null) {
                element.setSelectedOption(index);
                // 根据选择的option更新style的配置
                updateStyleDataBySelectOption(position);
            }
        }
    }

    /**
     * 根据选择的Option更新style的数据
     */
    private void updateStyleDataBySelectOption(int position) {
        Styles styles = mAssetPackage.getElementProvider().getStyles();
        if ((styles == null) || (styles.getStyles() == null)) {
            return;
        }
        Style style = styles.getStyle(styles.getSelectedStyle());
        if (style == null) {
            return;
        }
        String index = mOptionIndexList.get(position);
        switch (mLabel) {
            case UnitConstants.LABEL_BACKGROUND:
                style.setBackgroundSelectedOption(index);
                break;
            case UnitConstants.LABEL_DATE:
                style.setDateSelectedOption(index);
                break;
            case UnitConstants.LABEL_TIME:
                style.setTimeSelectedOption(index);
                break;
            case UnitConstants.LABEL_DIAL:
                style.setDialSelectedOption(index);
                break;
            case UnitConstants.LABEL_WIDGET:
                style.setContainerSelectedOptions(index);
                break;
            default:
                break;
        }
    }

    private void initStyleIndexList() {
        mStyleIndexList = new ArrayList<>(0);
        Styles style = mAssetPackage.getElementProvider().getStyles();
        if (style == null) {
            return;
        }
        List<Style> styles = style.getStyles();
        if ((styles == null) || (styles.size() <= 0)) {
            return;
        }
        for (Style sty : styles) {
            if (sty == null) {
                continue;
            }
            mStyleIndexList.add(WatchFaceUtil.getStringValue(sty.getIndex()));
        }
    }

    private void initOptionIndexList() {
        if (TextUtils.equals(mLabel, UnitConstants.LABEL_WIDGET)) {
            return;
        }
        mOptionIndexList = new ArrayList<>(0);
        Styles styles = mAssetPackage.getElementProvider().getStyles();
        if (styles != null) {
            String optionIndex = "";
            Style style = styles.getStyle(styles.getSelectedStyle());
            switch (mLabel) {
                case UnitConstants.LABEL_BACKGROUND:
                    LogUtil.e(TAG,"LABEL_BACKGROUND");
                    optionIndex = style.getBackgroundAvailableOption();
                    break;
                case UnitConstants.LABEL_DATE:
                    LogUtil.e(TAG,"LABEL_DATE");
                    optionIndex = style.getDateAvailableOption();
                    break;
                case UnitConstants.LABEL_TIME:
                    LogUtil.e(TAG,"LABEL_TIME");
                    optionIndex = style.getTimeAvailableOption();
                    break;
                case UnitConstants.LABEL_DIAL:
                    LogUtil.e(TAG,"LABEL_DIAL");
                    optionIndex = style.getDialAvailableOption();
                    break;
                default:
                    LogUtil.e(TAG,"default");
                    break;
            }
            LogUtil.e(TAG,"BOOLEAN :" + (optionIndex == null));
            if (optionIndex.contains(SPLIT_COMMA)) {
                List<String> optionIndexList = Arrays.asList(optionIndex.split(SPLIT_COMMA));
                mOptionIndexList.addAll(optionIndexList);
            }
        } else {
            Element element = mAssetPackage.getElementProvider().getElementWithLabel(mLabel);

            if (element == null) {
                return;
            }

            List<Option> options = element.getOptions();
            if ((options == null) || (options.size() <= 0)) {
                return;
            }
            for (Option option : options) {
                if (option == null) {
                    continue;
                }
                mOptionIndexList.add(WatchFaceUtil.getStringValue(option.getIndex()));
            }
        }
    }

    private void initContainerIndexList() {
        mContainerIndexList = new ArrayList<>(0);
        Element element = mAssetPackage.getElementProvider().getElementWithLabel(mLabel);
        if (element == null) {
            return;
        }

        List<Container> containers = element.getContainers();
        if ((containers == null) || (containers.size() <= 0)) {
            return;
        }
        for (Container container : containers) {
            if (container == null) {
                continue;
            }
            mContainerIndexList.add(WatchFaceUtil.getStringValue(container.getIndex()));
        }
    }

    private HashMap<String, Bitmap> getWidgetPreviewMap() {
        if ((mContainerIndexList == null) || (mContainerIndexList.size() <= 0)) {
            return new HashMap<>(0);
        }
        Element element = mAssetPackage.getElementProvider().getElementWithLabel(UnitConstants.LABEL_WIDGET);

        if (element == null) {
            return new HashMap<>(0);
        }
        HashMap<String, Bitmap> bitmapMap = new HashMap<>(0);
        for (String index : mContainerIndexList) {
            Container container = element.getContainer(index);
            if (container == null) {
                continue;
            }
            if (WatchFaceUtil.getBoolValue(container.getIsAvailable(), true)) {
                bitmapMap.put(index, mAssetPackage.getBitmap(element.getPreview(container.getIndex())));
            }
        }
        return bitmapMap;
    }

    private Bitmap getPreview(String label) {
        Element element = mAssetPackage.getElementProvider().getElementWithLabel(label);

        if (element == null) {
            return null;
        }

        Bitmap resultBitmap = mAssetPackage.getBitmap(element.getPreview());
        if (resultBitmap == null) {
            return null;
        }
        return resultBitmap;
    }

    private Bitmap getPreview(String label, int position) {
        String index;
        if (TextUtils.equals(mLabel, UnitConstants.STYLES)) {
            if ((mStyleIndexList == null) || (mStyleIndexList.size() <= 0)) {
                return null;
            }
            Styles styles = mAssetPackage.getElementProvider().getStyles();

            if (styles == null) {
                return null;
            }
            Style style = styles.getStyle(mStyleIndexList.get(position));

            if (style == null) {
                return null;
            }
            switch (label) {
                case UnitConstants.LABEL_BACKGROUND:
                    index = style.getBackgroundSelectedOption();
                    break;
                case UnitConstants.LABEL_TIME:
                    index = style.getTimeSelectedOption();
                    break;
                case UnitConstants.LABEL_DATE:
                    index = style.getDateSelectedOption();
                    break;
                case UnitConstants.LABEL_DIAL:
                    index = style.getDialSelectedOption();
                    break;
                default:
                    return null;
            }
        } else {
            if ((mOptionIndexList == null) || (mOptionIndexList.size() <= 0)) {
                return null;
            }
            index = mOptionIndexList.get(position);
        }
        Element element = mAssetPackage.getElementProvider().getElementWithLabel(label);

        if ((element == null) || (index == null)) {
            return null;
        }
        Option option = element.getOption(index);

        if (option == null) {
            return null;
        }
        Bitmap resultBitmap = mAssetPackage.getBitmap(option.getResPreview());
        if (resultBitmap == null) {
            return null;
        }
        return resultBitmap;
    }

    private HashMap<String, Bitmap> getBorderPreviewMap() {
        if ((mContainerIndexList == null) || (mContainerIndexList.size() <= 0)) {
            return new HashMap<>(0);
        }
        Element element = mAssetPackage.getElementProvider().getElementWithLabel(UnitConstants.LABEL_WIDGET);

        if (element == null) {
            return new HashMap<>(0);
        }
        HashMap<String, Bitmap> bitmapMap = new HashMap<>(0);
        for (String index : mContainerIndexList) {
            Container container = element.getContainer(index);

            if (container == null) {
                continue;
            }
            // 判断控件是否可用
            if (WatchFaceUtil.getBoolValue(container.getIsAvailable(), true)) {
                bitmapMap.put(index, mAssetPackage.getBitmap(element.getBorderPreview(container.getIndex())));
            }
        }
        return bitmapMap;
    }

    private boolean isHaveRandomBackground(String label, int position) {
        boolean isRandomOption = false;

        Element element = mAssetPackage.getElementProvider().getElementWithLabel(label);
        String index = mOptionIndexList.get(position);
        if (element == null) {
            return false;
        }
        Option option = element.getOption(index);
        if (option == null) {
            return false;
        }

        List<Layer> layerList = option.getLayers();
        for (Layer layer : layerList) {
            if (TextUtils.equals(layer.getDrawType(), UnitConstants.VALUE_DRAW_TYPE_RANDOM_RES)) {
                isRandomOption = true;
                break;
            }
        }
        return isRandomOption;
    }

    private void updateItemView(ItemView itemView, int position) {
        Bitmap preview;
        switch (mLabel) {
            case UnitConstants.LABEL_BACKGROUND:
                preview = getPreview(UnitConstants.LABEL_BACKGROUND, position);
                if (preview != null) {
                    itemView.setBgBitmap(preview);
                }
                itemView.setDrawRandomBackground(isHaveRandomBackground(UnitConstants.LABEL_BACKGROUND, position));
                preview = getPreview(UnitConstants.LABEL_TIME);
                if (preview != null) {
                    itemView.setTimeBitmap(preview);
                }
                preview = getPreview(UnitConstants.LABEL_DATE);
                if (preview != null) {
                    itemView.setDateBitmap(preview);
                }

                itemView.setWidgetBitmapMap(getWidgetPreviewMap());
                break;
            case UnitConstants.LABEL_TIME:
                preview = getPreview(UnitConstants.LABEL_BACKGROUND);
                if (preview != null) {
                    itemView.setBgBitmap(preview);
                }
                preview = getPreview(UnitConstants.LABEL_TIME, position);
                if (preview != null) {
                    itemView.setTimeBitmap(preview);
                }

                preview = getPreview(UnitConstants.LABEL_DATE);
                if (preview != null) {
                    itemView.setDateBitmap(preview);
                }

                preview = getPreview(UnitConstants.LABEL_DATE);
                if (preview != null) {
                    itemView.setDateBitmap(preview);
                }
                itemView.setWidgetBitmapMap(getWidgetPreviewMap());
                break;
            default:
                updateItemViewByLabel(itemView, position);
                break;
        }
        itemView.invalidate();
    }

    private void updateItemViewByLabel(ItemView itemView, int position) {
        Bitmap preview;
        switch (mLabel) {
            case UnitConstants.LABEL_DATE:
                preview = getPreview(UnitConstants.LABEL_BACKGROUND);
                if (preview != null) {
                    itemView.setBgBitmap(preview);
                }
                preview = getPreview(UnitConstants.LABEL_TIME);
                if (preview != null) {
                    itemView.setTimeBitmap(preview);
                }
                preview = getPreview(UnitConstants.LABEL_DATE);
                if (preview != null) {
                    itemView.setDateBitmap(preview);
                }
                itemView.setWidgetBitmapMap(getWidgetPreviewMap());
                break;
            case UnitConstants.LABEL_WIDGET:
                preview = getPreview(UnitConstants.LABEL_BACKGROUND);
                if (preview != null) {
                    itemView.setBgBitmap(preview);
                }
                preview = getPreview(UnitConstants.LABEL_TIME);
                if (preview != null) {
                    itemView.setTimeBitmap(preview);
                }
                preview = getPreview(UnitConstants.LABEL_DATE);
                if (preview != null) {
                    itemView.setDateBitmap(preview);
                }
                itemView.setBorderBitmapMap(getBorderPreviewMap());
                itemView.setWidgetBitmapMap(getWidgetPreviewMap());
                break;
            case UnitConstants.STYLES:
                preview = getPreview(UnitConstants.LABEL_BACKGROUND, position);
                if (preview != null) {
                    itemView.setBgBitmap(preview);
                }
                preview = getPreview(UnitConstants.LABEL_TIME, position);
                if (preview != null) {
                    itemView.setTimeBitmap(preview);
                }
                preview = getPreview(UnitConstants.LABEL_DIAL, position);
                if (preview != null) {
                    itemView.setDialBitmap(preview);
                }
                preview = getPreview(UnitConstants.LABEL_DATE, position);
                if (preview != null) {
                    itemView.setDateBitmap(preview);
                }
                itemView.setWidgetBitmapMap(getWidgetPreviewMap());
                break;
            case UnitConstants.LABEL_DIAL:

                preview = getPreview(UnitConstants.LABEL_BACKGROUND);
                if (preview != null) {
                    itemView.setBgBitmap(preview);
                }
                preview = getPreview(UnitConstants.LABEL_TIME);
                if (preview != null) {
                    itemView.setTimeBitmap(preview);
                }
                preview = getPreview(UnitConstants.LABEL_DIAL, position);
                if (preview != null) {
                    itemView.setDialBitmap(preview);
                }
                preview = getPreview(UnitConstants.LABEL_DATE, position);
                if (preview != null) {
                    itemView.setDateBitmap(preview);
                }
                itemView.setWidgetBitmapMap(getWidgetPreviewMap());
                break;
            default:
                break;
        }
    }

    /**
     * 界面刷新
     */
    public void notifyDataChanged() {
        mItemViews.clear();
        initOptionIndexList();
        notifyDataSetChanged();
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class ItemView extends View {
        private static final String TAG = "ItemView";

        private static final int SCREEN_SIZE = 454;

        private static final float SCALE_CENTER = SCREEN_SIZE * 0.5f;

        private static final float SCALE_AMOUNT = 0.65f;

        private static final float SCALE_HALF = 0.5f;

        private BlueCircleView mBackgroundCircle;

        private RandomBackgroundView mRandomBackgroundView;

        private Bitmap mBgBitmap;

        private Bitmap mTimeBitmap;

        private Bitmap mDateBitmap;

        private Bitmap mDialBitmap;

        private HashMap<String, Bitmap> mWidgetBitmapMap;

        private HashMap<String, Rect> mWidgetRectMap;

        private HashMap<String, Bitmap> mBorderBitmapMap;

        private HashMap<String, BlueCircleView> mWidgetCircles;

        private boolean mIsDrawRandomBackground;

        private Paint mPaint;

        private Rect mWidgetMaskBgBound;

        private PaintFlagsDrawFilter mPaintFlagsDrawFilter;

        private HashMap<String, Float> mRotateDegree;

        private HashMap<String, Float> mCenterDegree;

        private HashMap<String, Bitmap> mContainerRotateBitmap;

        private HashMap<String, Float> mResRadians;

        private HashMap<String, String> mIsArcLinear;

        ItemView(Context context) {
            super(context);
            mRotateDegree = new HashMap<>(0);
            mCenterDegree = new HashMap<>(0);
            mResRadians = new HashMap<>(0);
            mIsArcLinear = new HashMap<>(0);
            int size = WatchFaceUtil.getDisplayMetrics(mContext);
            mWidgetMaskBgBound = new Rect(0, 0, size, size);
            mBackgroundCircle = new BlueCircleView(mContext, new Rect(0, 0, size, size));
            mRandomBackgroundView = new RandomBackgroundView(mContext, new Rect(0, 0, size, size),
                    R.string.random_background);

            int bits = Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG;
            mPaintFlagsDrawFilter = new PaintFlagsDrawFilter(0, bits);
            mPaint = new Paint();
            mPaint.setAntiAlias(true);

            if ((mContainerIndexList == null) || (mContainerIndexList.size() <= 0)) {
                return;
            }
            Element widget = mAssetPackage.getElementProvider().getElementWithLabel(UnitConstants.LABEL_WIDGET);
            if (widget == null) {
                return;
            }
            mWidgetRectMap = new HashMap<>(0);
            mWidgetCircles = new HashMap<>(0);
            for (String index : mContainerIndexList) {
                Container optionalContainer = widget.getContainer(index);
                if (optionalContainer == null) {
                    continue;
                }
                LogUtil.e(TAG,"mContainerIndexList " + mContainerIndexList.size());
                // 判断控件是否可用
                Container container = optionalContainer;
                if (TextUtils.isEmpty(container.getIsAvailable()) || WatchFaceUtil.getBoolValue(container.getIsAvailable())) {
                    LogUtil.e(TAG,"container" + index);
                    mWidgetCircles.put(index, new BlueCircleView(mContext, WatchFaceUtil.getRect(container.getRect())));
                    mWidgetRectMap.put(index, WatchFaceUtil.getRect(container.getRect()));
                }
            }
        }

        public void setBgBitmap(Bitmap bgBitmap) {
            mBgBitmap = bgBitmap;
        }

        public void setTimeBitmap(Bitmap timeBitmap) {
            mTimeBitmap = timeBitmap;
        }

        public void setDateBitmap(Bitmap dateBitmap) {
            mDateBitmap = dateBitmap;
        }

        public void setDialBitmap(Bitmap dialBitmap) {
            mDialBitmap = dialBitmap;
        }

        public void setWidgetBitmapMap(HashMap<String, Bitmap> widgetBitmapMap) {
            mWidgetBitmapMap = widgetBitmapMap;
        }

        public void setBorderBitmapMap(HashMap<String, Bitmap> borderBitmapMap) {
            mBorderBitmapMap = borderBitmapMap;
        }

        public void setDrawRandomBackground(boolean isDrawRandomBackground) {
            mIsDrawRandomBackground = isDrawRandomBackground;
        }

        public void setContainerRotateBitmap(HashMap<String, Bitmap> containerRotateBitmap) {
            mContainerRotateBitmap = containerRotateBitmap;
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension(SCREEN_SIZE, SCREEN_SIZE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.save();
            canvas.scale(SCALE_AMOUNT, SCALE_AMOUNT, SCALE_CENTER, SCALE_CENTER);
            canvas.setDrawFilter(mPaintFlagsDrawFilter);
            // 绘制背景
            if (mBgBitmap != null) {
                canvas.drawBitmap(mBgBitmap, 0, 0, mPaint);
            }

            if (mArcBackgroundPreview != null) {
                canvas.drawBitmap(mArcBackgroundPreview, 0, 0, mPaint);
            }

            // 绘制刻度
            if (mDialBitmap != null) {
                canvas.drawBitmap(mDialBitmap, 0, 0, mPaint);
            }

            // 绘制时间
            if (mTimeBitmap != null) {
                canvas.drawBitmap(mTimeBitmap, 0, 0, mPaint);
            }

            // 绘制日期
            if (mDateBitmap != null) {
                canvas.drawBitmap(mDateBitmap, 0, 0, mPaint);
            }

            // 绘制widget
            drawWidget(canvas);
            if (mIsDrawRandomBackground) {
                mRandomBackgroundView.draw(canvas);
            }
            canvas.restore();
        }

        private void drawWidget(Canvas canvas) {
            if ((mContainerIndexList != null) && (mContainerIndexList.size() > 0)) {
                mWidgetMaskBg.setBounds(mWidgetMaskBgBound);
                mWidgetMaskBg.draw(canvas);
                int count = 0;
                for (String index : mContainerIndexList) {
                    Rect rect = mWidgetRectMap.get(index);
                    Bitmap bitmap = mWidgetBitmapMap.get(index);

                    if ((rect != null)) {
                        // 绘制图片
                        if (bitmap != null) {
                            int viewPointX = getCoordinateValues(bitmap, rect).x;
                            int viewPointY = getCoordinateValues(bitmap, rect).y;
                            canvas.drawBitmap(bitmap, viewPointX, viewPointY, mPaint);
                        }
                        // 绘制边框
                        Bitmap borderBitMap = mBorderBitmapMap.get(index);
                        if (borderBitMap != null) {
                            int borderPointX = getCoordinateValues(borderBitMap, rect).x;
                            int borderPointY = getCoordinateValues(borderBitMap, rect).y;
                            canvas.drawBitmap(borderBitMap, borderPointX, borderPointY, mPaint);
                        } else {
                            mWidgetCircles.get(index).draw(canvas);
                        }
                    } else if (rect != null) {
                        if (bitmap != null) {
                            Bitmap containerRotateBitmap = mContainerRotateBitmap.get(index);
                            if ((containerRotateBitmap != null) && (count == 0)) {
                                canvas.drawBitmap(containerRotateBitmap, 0, 0, mPaint);
                            }
                            if (containerRotateBitmap != null) {
                                count++;
                            }
                        }
                    }
                }
            }
        }

        private Point getCoordinateValues(Bitmap bitmap, Rect rect) {
            Point point = new Point();
            double middlex = rect.left + SCALE_HALF * (rect.right - rect.left);
            double middley = rect.top + SCALE_HALF * (rect.bottom - rect.top);
            // 获取绘制的bitmap的宽度和高度
            int bitmapHeight = bitmap.getHeight();
            int bitmapWidth = bitmap.getWidth();
            // 计算出绘图左上角的坐标值
            int pointX = (int) (middlex - SCALE_HALF * bitmapWidth);
            int pointY = (int) (middley - SCALE_HALF * bitmapHeight);
            point.x = pointX;
            point.y = pointY;
            return point;
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (!TextUtils.equals(mLabel, UnitConstants.LABEL_WIDGET)) {
                return true;
            }
            if ((mContainerIndexList == null) || (mContainerIndexList.size() <= 0)) {
                return true;
            }
            if (event.getAction() != MotionEvent.ACTION_UP) {
                return true;
            }
            int x = (int) event.getX();
            int y = (int) event.getY();
            for (String index : mContainerIndexList) {
                Rect rect = mWidgetRectMap.get(index);
                if (WatchFaceUtil.isInBound(x, y, rect)) {
                    Element element = mAssetPackage.getElementProvider().getElementWithLabel(
                            UnitConstants.LABEL_WIDGET);
                    Container container;
                    if (element != null) {
                        container = element.getContainer(index);
                    } else {
                        return true;
                    }
                    if ((container != null) && (WatchFaceUtil.getBoolValue(container.getIsSupportOption()))) {
                        Intent intent = new Intent();
                        intent.setPackage(mContext.getPackageName());
                        intent.setAction(CommonConstantsUtil.ACTION_START_WIDGET_OPTION_LIST);
                        intent.putExtra(CommonConstantsUtil.EXTRA_CONTAINER_INDEX, index);
                        mContext.startActivity(intent);
                        break;
                    }
                }
            }
            return true;
        }
    }

    private void showBlueBg(String label, RelativeLayout blueBorder) {
        switch (label) {
            case UnitConstants.STYLES:
            case UnitConstants.LABEL_BACKGROUND:
            case UnitConstants.LABEL_DIAL:
                blueBorder.setVisibility(View.VISIBLE);
                break;
            default:
                blueBorder.setVisibility(View.GONE);
        }
    }
}
