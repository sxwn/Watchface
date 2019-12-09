package com.goertek.watchface.dataprovider.worldtime.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.goertek.watchface.R;
import com.goertek.watchface.dataprovider.worldtime.bean.TimeZoneBean;

import java.util.List;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class TimeZoneAdapter extends ArrayAdapter {
    private static final String TAG = "TimeZoneAdapter";

    private static final String FONT_STYLE = "fonts/HWtext-55ST.ttf";

    private Context mContext;

    private int mResourceId;

    private List<TimeZoneBean> timeZoneList;

    public TimeZoneAdapter(@NonNull Context context, int resId, List<TimeZoneBean> timeZoneBeans) {
        super(context, resId, timeZoneBeans);
        this.mContext = context;
        this.mResourceId = resId;
        this.timeZoneList = timeZoneBeans;
    }

    public int getCount() {
        return this.timeZoneList.size();
    }

    public TimeZoneBean getItem(int position) {
        return (TimeZoneBean)this.timeZoneList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TimeZoneBean timeZoneBean = this.getItem(position);
        ViewHolder viewHolder = new ViewHolder();
        ViewHolder holder;
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(this.mContext).inflate(this.mResourceId, parent, false);
            viewHolder.cityNameTV = view.findViewById(R.id.city_name);
            viewHolder.cityCodeTV = view.findViewById(R.id.city_code);
            viewHolder.timeZoneTv = view.findViewById(R.id.city_time_zone);
            view.setTag(viewHolder);
            holder = viewHolder;
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
            view = convertView;
            holder = viewHolder;
        }

        Typeface typeface = Typeface.createFromAsset(this.getContext().getAssets(), "fonts/HWtext-55ST.ttf");
        holder.cityNameTV.setTypeface(typeface);
        holder.cityNameTV.setText(timeZoneBean.getCityName());
        holder.cityCodeTV.setTypeface(typeface);
        holder.cityCodeTV.setText(timeZoneBean.getCityCode());
        holder.timeZoneTv.setTypeface(typeface);
        holder.timeZoneTv.setText(timeZoneBean.getGmtOffSet());
        return view;
    }
}