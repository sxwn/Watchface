package com.goertek.watchface.dataprovider.worldtime.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.goertek.watchface.R;
import com.goertek.watchface.dataprovider.worldtime.bean.ContinentBean;

import java.util.List;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class ContinentAdapter extends ArrayAdapter<ContinentBean> {
    private static final String FONT_STYLE = "fonts/HYQiHei-60S.otf";
    private Context mContext;
    private List<ContinentBean> mContinentBeans;
    private int mResourceId;

    public ContinentAdapter(Context context, int resId, List<ContinentBean> continentBeans) {
        super(context, resId, continentBeans);
        this.mContext = context;
        this.mResourceId = resId;
        this.mContinentBeans = continentBeans;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ContinentBean continentBean = getItem(position);
        ContinentAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(this.mResourceId, parent, false);
            viewHolder = new ContinentAdapter.ViewHolder();
            viewHolder.continentName = convertView.findViewById(R.id.continent_item);
            viewHolder.continentImage = convertView.findViewById(R.id.continent_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ContinentAdapter.ViewHolder) convertView.getTag();
        }

        String continentName = continentBean.getContinentName();
        Typeface typeface = Typeface.createFromAsset(this.getContext().getAssets(), "fonts/HYQiHei-60S.otf");
        viewHolder.continentName.setTypeface(typeface);
        viewHolder.continentName.setText(continentName);
        Drawable imageResource = continentBean.getImageResource();
        viewHolder.continentImage.setImageDrawable(imageResource);
        return convertView;
    }

    class ViewHolder {
        private ImageView continentImage;
        private TextView continentName;

        ViewHolder() {
        }
    }
}
