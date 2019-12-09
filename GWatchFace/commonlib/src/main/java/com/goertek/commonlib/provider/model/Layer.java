package com.goertek.commonlib.provider.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Layer {
    @SerializedName("@index")
    private String index;

    @SerializedName("@draw_type")
    private String drawType;

    /**
     * select layer
     */
    @SerializedName("layer")
    private List<Layer> layers;

    /**
     * text
     */
    @SerializedName("@text_content")
    private String textContent;

    @SerializedName("@text_active_color")
    private String textActiveColor;

    @SerializedName("@text_ambient_color")
    private String textAmbientColor;

    @SerializedName("@text_font")
    private String textFont;

    @SerializedName("@text_size")
    private String textSize;

    @SerializedName("@text_align")
    private String textAlign;

    @SerializedName("@text_position")
    private String textPosition;

    @SerializedName("@text_position_relative")
    private String textPositionRelative;

    @SerializedName("@word_support_cn")
    private String wordSupportCN;

    @SerializedName("@word_is_abbr")
    private String wordIsAbbr;

    @SerializedName("@word_capital_type")
    private String wordCapitalType;

    @SerializedName("@text_is_bold")
    private String textIsBold;

    @SerializedName("@arc_linear_margin")
    private String arcLinearMargin;

    @SerializedName("@arc_linear_bitmap_rotate")
    private String arcLinearBitmapRotate;

    @SerializedName("@arc_linear_selected_container")
    private String arcLinearSelectedContainer;

    /**
     * res
     */
    @SerializedName("@res_active")
    private String resActive;

    @SerializedName("@res_active_left")
    private String resActiveLeft;

    @SerializedName("@res_active_right")
    private String resActiveRight;

    @SerializedName("@res_active_dot")
    private String resActiveDot;

    @SerializedName("@res_active_scale")
    private String resActiveScale;

    @SerializedName("@res_ambient")
    private String resAmbient;

    @SerializedName("@res_ambient_left")
    private String resAmbientLeft;

    @SerializedName("@res_ambient_right")
    private String resAmbientRight;

    @SerializedName("@res_ambient_dot")
    private String resAmbientDot;

    @SerializedName("@res_ambient_scale")
    private String resAmbientScale;

    @SerializedName("@res_shadow")
    private String resShadow;

    @SerializedName("@res_shadow_is_only_show_active")
    private String resShadowIsOnlyShowActive;

    @SerializedName("@res_dot_shadow")
    private String resDotShadow;

    @SerializedName("@res_interval")
    private String resInterval;

    @SerializedName("@res_align")
    private String resAlign;

    @SerializedName("@res_position")
    private String resPosition;

    @SerializedName("@res_position_relative")
    private String resPositionRelative;

    @SerializedName("@res_ordered_is_play_once")
    private String resOrderedIsPlayOnce;

    @SerializedName("@rotate_point_hand")
    private String rotatePointHand;

    @SerializedName("@rotate_point_face")
    private String rotatePointFace;

    @SerializedName("@rotate_point_face_relative")
    private String rotatePointFaceRelative;

    @SerializedName("@rotate_start_angel")
    private String rotateStartAngel;

    @SerializedName("@rotate_end_angel")
    private String rotateEndAngel;

    /**
     * shape
     */
    @SerializedName("@primary_color")
    private String primaryColor;

    @SerializedName("@secondary_color")
    private String secondaryColor;

    @SerializedName("@line_width")
    private String lineWidth;

    @SerializedName("@line_cap")
    private String lineCap;

    @SerializedName("@start_point")
    private String startPoint;

    @SerializedName("@end_point")
    private String endPoint;

    @SerializedName("@start_point_relative")
    private String startPointRelative;

    @SerializedName("@end_point_relative")
    private String endPointRelative;

    @SerializedName("@start_angle")
    private String startAngle;

    @SerializedName("@end_angle")
    private String endAngle;

    @SerializedName("@rect")
    private String rect;

    @SerializedName("@rect_relative")
    private String rectRelative;

    @SerializedName("@arc_ambient_draw")
    private String arcAmbientDraw;

    /**
     * complication
     */
    @SerializedName("@background_color")
    private String backgroundColor;

    @SerializedName("@background_drawable")
    private String backgroundDrawable;

    @SerializedName("@border_color")
    private String borderColor;

    @SerializedName("@highlight_color")
    private String highlightColor;

    @SerializedName("@icon_color")
    private String iconColor;

    @SerializedName("@text_color")
    private String textColor;

    @SerializedName("@title_color")
    private String titleColor;

    @SerializedName("@title_font")
    private String titleFont;

    @SerializedName("@title_size")
    private String titleSize;

    @SerializedName("@range_primary_color")
    private String rangePrimaryColor;

    @SerializedName("@range_secondary_color")
    private String rangeSecondaryColor;

    @SerializedName("@bounds_rect")
    private String boundsRect;

    /**
     * rotate
     */
    @SerializedName("@rotate_type")
    private String rotateType;

    @SerializedName("@rotate_fixed_degree")
    private String rotateFixedDegree;

    @SerializedName("@rotate_center_point")
    private String rotateCenterPoint;

    @SerializedName("@rotate_center_point_relative")
    private String rotateCenterPointRelative;

    @SerializedName("@rotate_degree")
    private String rotateDegree;

    @SerializedName("@rotate_time")
    private String rotateTime;

    @SerializedName("@rotate_motion_type")
    private String rotateMotionType;

    /**
     * scale
     */
    @SerializedName("@scale_type")
    private String scaleType;

    @SerializedName("@scale_center_point")
    private String scaleCenterPoint;

    @SerializedName("@scale_center_point_relative")
    private String scaleCenterPointRelative;

    @SerializedName("@scale_amount")
    private String scaleAmount;

    @SerializedName("@scale_time")
    private String scaleTime;

    @SerializedName("@scale_motion_type")
    private String scaleMotionType;

    /**
     * translate
     */
    @SerializedName("@translate_type")
    private String translateType;

    @SerializedName("@translate_end_position")
    private String translateEndPosition;

    @SerializedName("@translate_end_position_relative")
    private String translateEndPositionRelative;

    /**
     * value
     */
    @SerializedName("@value_type")
    private String valueType;

    @SerializedName("@text_font_options")
    private String textFontOptions;

    @SerializedName("@text_position_label")
    private String textPositionLabel;

    @SerializedName("@text_position_options")
    private String textPositionOptions;

    @SerializedName("@text_position_labels")
    private String textPositionLabels;

    @SerializedName("@shadow_offset")
    private String shadowOffSet;

    @SerializedName("@text_shadow_position")
    private String textShadowPosition;

    @SerializedName("@text_shadow_color")
    private String textShadowColor;

    @SerializedName("@text_shadow_radius")
    private String textShadowRadius;

    @SerializedName("@is_support_text_shadow")
    private String isSupportTextShadow;

    public String getIndex() {
        return index;
    }

    public String getDrawType() {
        return drawType;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public String getTextContent() {
        return textContent;
    }

    public String getTextActiveColor() {
        return textActiveColor;
    }

    public String getTextAmbientColor() {
        return textAmbientColor;
    }

    public String getTextFont() {
        return textFont;
    }

    public String getTextSize() {
        return textSize;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public String getTextPosition() {
        return textPosition;
    }

    public String getTextPositionRelative() {
        return textPositionRelative;
    }

    public String getTextIsBold() {
        return textIsBold;
    }

    public String getWordSupportCN() {
        return wordSupportCN;
    }

    public String getWordIsAbbr() {
        return wordIsAbbr;
    }

    public String getWordCapitalType() {
        return wordCapitalType;
    }

    public String getResActive() {
        return resActive;
    }

    public String getResActiveLeft() {
        return resActiveLeft;
    }

    public String getResActiveRight() {
        return resActiveRight;
    }

    public String getResActiveDot() {
        return resActiveDot;
    }

    public String getResActiveScale() {
        return resActiveScale;
    }

    public String getResAmbient() {
        return resAmbient;
    }

    public String getResAmbientLeft() {
        return resAmbientLeft;
    }

    public String getResAmbientRight() {
        return resAmbientRight;
    }

    public String getResAmbientDot() {
        return resAmbientDot;
    }

    public String getResAmbientScale() {
        return resAmbientScale;
    }

    public String getResShadow() {
        return resShadow;
    }

    public String getResShadowIsOnlyShowActive() {
        return resShadowIsOnlyShowActive;
    }

    public String getResDotShadow() {
        return resDotShadow;
    }

    public String getResInterval() {
        return resInterval;
    }

    public String getResAlign() {
        return resAlign;
    }

    public String getResPosition() {
        return resPosition;
    }

    public String getResPositionRelative() {
        return resPositionRelative;
    }

    public String getResOrderedIsPlayOnce() {
        return resOrderedIsPlayOnce;
    }

    public String getRotatePointHand() {
        return rotatePointHand;
    }

    public String getRotatePointFace() {
        return rotatePointFace;
    }

    public String getRotatePointFaceRelative() {
        return rotatePointFaceRelative;
    }

    public String getRotateStartAngel() {
        return rotateStartAngel;
    }

    public String getRotateEndAngel() {
        return rotateEndAngel;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public String getLineWidth() {
        return lineWidth;
    }

    public String getLineCap() {
        return lineCap;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String getStartPointRelative() {
        return startPointRelative;
    }

    public String getEndPointRelative() {
        return endPointRelative;
    }

    public String getStartAngle() {
        return startAngle;
    }

    public String getEndAngle() {
        return endAngle;
    }

    public String getRect() {
        return rect;
    }

    public String getRectRelative() {
        return rectRelative;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getBackgroundDrawable() {
        return backgroundDrawable;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public String getHighlightColor() {
        return highlightColor;
    }

    public String getIconColor() {
        return iconColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public String getTitleFont() {
        return titleFont;
    }

    public String getTitleSize() {
        return titleSize;
    }

    public String getRangePrimaryColor() {
        return rangePrimaryColor;
    }

    public String getRangeSecondaryColor() {
        return rangeSecondaryColor;
    }

    public String getBoundsRect() {
        return boundsRect;
    }

    public String getRotateType() {
        return rotateType;
    }

    public String getRotateFixedDegree() {
        return rotateFixedDegree;
    }

    public String getRotateCenterPoint() {
        return rotateCenterPoint;
    }

    public String getRotateCenterPointRelative() {
        return rotateCenterPointRelative;
    }

    public String getRotateDegree() {
        return rotateDegree;
    }

    public String getRotateTime() {
        return rotateTime;
    }

    public String getRotateMotionType() {
        return rotateMotionType;
    }

    public String getScaleType() {
        return scaleType;
    }

    public String getScaleCenterPoint() {
        return scaleCenterPoint;
    }

    public String getScaleCenterPointRelative() {
        return scaleCenterPointRelative;
    }

    public String getScaleAmount() {
        return scaleAmount;
    }

    public String getScaleTime() {
        return scaleTime;
    }

    public String getScaleMotionType() {
        return scaleMotionType;
    }

    public String getTranslateType() {
        return translateType;
    }

    public String getTranslateEndPosition() {
        return translateEndPosition;
    }

    public String getTranslateEndPositionRelative() {
        return translateEndPositionRelative;
    }

    public String getValueType() {
        return valueType;
    }

    public String getTextFontOptions() {
        return textFontOptions;
    }

    public String getTextPositionLabel() {
        return textPositionLabel;
    }

    public String getTextPositionOptions() {
        return textPositionOptions;
    }

    public String getTextPositionLabels() {
        return textPositionLabels;
    }

    public String getShadowOffSet() {
        return shadowOffSet;
    }

    public void setTextFontOptions(String textFontOptions) {
        this.textFontOptions = textFontOptions;
    }

    public void setTextPositionLabel(String textPositionLabel) {
        this.textPositionLabel = textPositionLabel;
    }

    public void setTextPositionOptions(String textPositionOptions) {
        this.textPositionOptions = textPositionOptions;
    }

    public void setTextPositionLabels(String textPositionLabels) {
        this.textPositionLabels = textPositionLabels;
    }

    public void setTextFont(String textFont) {
        this.textFont = textFont;
    }

    public void setTextPosition(String textPosition) {
        this.textPosition = textPosition;
    }

    public void setTextPositionRelative(String textPositionRelative) {
        this.textPositionRelative = textPositionRelative;
    }

    public String getTextShadowPosition() {
        return textShadowPosition;
    }

    public void setTextShadowPosition(String textShadowPosition) {
        this.textShadowPosition = textShadowPosition;
    }

    public String getTextShadowColor() {
        return textShadowColor;
    }

    public void setTextShadowColor(String textShadowColor) {
        this.textShadowColor = textShadowColor;
    }

    public String getTextShadowRadius() {
        return textShadowRadius;
    }

    public void setTextShadowRadius(String textShadowRadius) {
        this.textShadowRadius = textShadowRadius;
    }

    public String getIsSupportTextShadow() {
        return isSupportTextShadow;
    }

    public String getArcLinearMargin() {
        return arcLinearMargin;
    }

    public String getArcLinearBitmapRotate() {
        return arcLinearBitmapRotate;
    }

    public String getArcLinearSelectedContainer() {
        return arcLinearSelectedContainer;
    }

    public void setArcLinearSelectedContainer(String arcLinearSelectedContainer) {
        this.arcLinearSelectedContainer = arcLinearSelectedContainer;
    }

    public String getArcAmbientDraw() {
        return arcAmbientDraw;
    }

    public void setArcAmbientDraw(String arcAmbientDraw) {
        this.arcAmbientDraw = arcAmbientDraw;
    }

    @Override
    public String toString() {
        return "Layer{" + "index='" + index + '\'' + ", drawType='" + drawType + '\'' + ", layers=" + layers
                       + ", textContent='" + textContent + '\'' + ", textActiveColor='" + textActiveColor + '\''
                       + ", textAmbientColor='" + textAmbientColor + '\'' + ", textFont='" + textFont + '\'' + ", textSize='"
                       + textSize + '\'' + ", textAlign='" + textAlign + '\'' + ", textPosition='" + textPosition + '\''
                       + ", textPositionRelative='" + textPositionRelative + '\'' + ", wordSupportCN='" + wordSupportCN + '\''
                       + ", wordIsAbbr='" + wordIsAbbr + '\'' + ", wordCapitalType='" + wordCapitalType + '\'' + ", textIsBold='"
                       + textIsBold + '\'' + ", resActive='" + resActive + '\'' + ", resActiveLeft='" + resActiveLeft + '\''
                       + ", resActiveRight='" + resActiveRight + '\'' + ", resActiveDot='" + resActiveDot + '\''
                       + ", resActiveScale='" + resActiveScale + '\'' + ", resAmbient='" + resAmbient + '\'' + ", resAmbientLeft='"
                       + resAmbientLeft + '\'' + ", resAmbientRight='" + resAmbientRight + '\'' + ", resAmbientDot='"
                       + resAmbientDot + '\'' + ", resAmbientScale='" + resAmbientScale + '\'' + ", resShadow='" + resShadow + '\''
                       + ", resShadowIsOnlyShowActive='" + resShadowIsOnlyShowActive + '\'' + ", resDotShadow='" + resDotShadow
                       + '\'' + ", resInterval='" + resInterval + '\'' + ", resAlign='" + resAlign + '\'' + ", resPosition='"
                       + resPosition + '\'' + ", resPositionRelative='" + resPositionRelative + '\'' + ", resOrderedIsPlayOnce='"
                       + resOrderedIsPlayOnce + '\'' + ", rotatePointHand='" + rotatePointHand + '\'' + ", rotatePointFace='"
                       + rotatePointFace + '\'' + ", rotatePointFaceRelative='" + rotatePointFaceRelative + '\''
                       + ", rotateStartAngel='" + rotateStartAngel + '\'' + ", rotateEndAngel='" + rotateEndAngel + '\''
                       + ", primaryColor='" + primaryColor + '\'' + ", secondaryColor='" + secondaryColor + '\'' + ", lineWidth='"
                       + lineWidth + '\'' + ", lineCap='" + lineCap + '\'' + ", startPoint='" + startPoint + '\'' + ", endPoint='"
                       + endPoint + '\'' + ", startPointRelative='" + startPointRelative + '\'' + ", endPointRelative='"
                       + endPointRelative + '\'' + ", startAngle='" + startAngle + '\'' + ", endAngle='" + endAngle + '\''
                       + ", rect='" + rect + '\'' + ", rectRelative='" + rectRelative + '\'' + ", backgroundColor='"
                       + backgroundColor + '\'' + ", backgroundDrawable='" + backgroundDrawable + '\'' + ", borderColor='"
                       + borderColor + '\'' + ", highlightColor='" + highlightColor + '\'' + ", iconColor='" + iconColor + '\''
                       + ", textColor='" + textColor + '\'' + ", titleColor='" + titleColor + '\'' + ", titleFont='" + titleFont
                       + '\'' + ", titleSize='" + titleSize + '\'' + ", rangePrimaryColor='" + rangePrimaryColor + '\''
                       + ", rangeSecondaryColor='" + rangeSecondaryColor + '\'' + ", boundsRect='" + boundsRect + '\''
                       + ", rotateType='" + rotateType + '\'' + ", rotateFixedDegree='" + rotateFixedDegree + '\''
                       + ", rotateCenterPoint='" + rotateCenterPoint + '\'' + ", rotateCenterPointRelative='"
                       + rotateCenterPointRelative + '\'' + ", rotateDegree='" + rotateDegree + '\'' + ", rotateTime='"
                       + rotateTime + '\'' + ", rotateMotionType='" + rotateMotionType + '\'' + ", scaleType='" + scaleType + '\''
                       + ", scaleCenterPoint='" + scaleCenterPoint + '\'' + ", scaleCenterPointRelative='"
                       + scaleCenterPointRelative + '\'' + ", scaleAmount='" + scaleAmount + '\'' + ", scaleTime='" + scaleTime
                       + '\'' + ", scaleMotionType='" + scaleMotionType + '\'' + ", translateType='" + translateType + '\''
                       + ", translateEndPosition='" + translateEndPosition + '\'' + ", translateEndPositionRelative='"
                       + translateEndPositionRelative + '\'' + ", valueType='" + valueType + '\'' + ", textFontOptions='"
                       + textFontOptions + '\'' + ", textPositionLabel='" + textPositionLabel + '\'' + ", textPositionOptions='"
                       + textPositionOptions + '\'' + ", textPositionLabels='" + textPositionLabels + '\'' + ", shadowOffSet='"
                       + shadowOffSet + '\'' + ", textShadowPosition='" + textShadowPosition + '\'' + ", textShadowColor='"
                       + textShadowColor + '\'' + ", textShadowRadius='" + textShadowRadius + '\'' + ", isSupportTextShadow='"
                       + isSupportTextShadow + '\'' + '}';
    }
}