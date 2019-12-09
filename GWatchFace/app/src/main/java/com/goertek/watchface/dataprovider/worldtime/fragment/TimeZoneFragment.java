package com.goertek.watchface.dataprovider.worldtime.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.goertek.commonlib.utils.LogUtil;
import com.goertek.watchface.R;
import com.goertek.watchface.dataprovider.worldtime.adapter.TimeZoneAdapter;
import com.goertek.watchface.dataprovider.worldtime.bean.TimeZoneBean;
import com.goertek.watchface.dataprovider.worldtime.utils.WorldTimeSharedPreferencesUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class TimeZoneFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final int CONTINENT_ID_AFICA = 1;
    private static final int CONTINENT_ID_ASIA = 0;
    private static final int CONTINENT_ID_AUSTRALIA = 4;
    private static final int CONTINENT_ID_EUROPE = 5;
    private static final int CONTINENT_ID_NORTH_AMERICA = 2;
    private static final int CONTINENT_ID_SOUTH_AMERICA = 3;
    private static final long MINUTE_TO_MILLS = 60000L;
    private static final String TAG = "TimeZoneFragment";
    private List<String> mCityCodeList;
    private List<String> mCityNameList;
    private List<String> mGmtOffsetList;
    private List<TimeZoneBean> mTimeZoneBeanList;
    private List<String> mTimeZoneList;

    public TimeZoneFragment() {
    }

    private static void appendNumber(StringBuilder builder, int count, int value) {
        String string = Integer.toString(value);
        for (int i = 0; i < count - string.length(); i++) {
            builder.append('0');
        }
        builder.append(string);
    }

    private List<String> calcGmtOffset() {
        List<String> list = new ArrayList(0);
        for (String timeZone : mTimeZoneList) {
            if (!TextUtils.isEmpty(timeZone)) {
                list.add(timeZone);
            } else {
                list.add("");
            }
        }
        return list;
    }

    public static String createGmtOffsetString(boolean includeGmt, boolean includeMinuteSeparator, int offsetMillis) {
        int offsetMinutes = offsetMillis / 60000;
        char sign;
        if (offsetMinutes < 0L) {
            sign = '-';
            offsetMinutes = -offsetMinutes;
        } else {
            sign = '+';
        }

        StringBuilder builder = new StringBuilder(9);
        if (includeGmt) {
            builder.append("GMT");
        }

        builder.append(sign);
        appendNumber(builder, 2, offsetMinutes / 60);
        if (includeMinuteSeparator) {
            builder.append(':');
        }

        appendNumber(builder, 2, offsetMinutes % 60);
        return builder.toString();
    }

    private List<String> getArrayById(int key) {
        return Arrays.asList(this.getResources().getStringArray(key));
    }

    public static String getCurrentTimeZone(String s) {
        TimeZone timeZone = TimeZone.getTimeZone(s);
        return createGmtOffsetString(true, true, timeZone.getOffset(Calendar.getInstance(timeZone).getTime().getTime()));
    }

    private void initData() {
        int cityNameListSize = 0;
        this.mTimeZoneBeanList = new ArrayList(0);
        this.loadCityList(this.getArguments().getInt("position"));

        for (this.mGmtOffsetList = this.calcGmtOffset(); cityNameListSize < this.mCityNameList.size(); ++cityNameListSize) {
            TimeZoneBean timeZoneBean = new TimeZoneBean((String) this.mCityNameList.get(cityNameListSize), (String) this.mCityCodeList.get(cityNameListSize), (String) this.mGmtOffsetList.get(cityNameListSize));
            this.mTimeZoneBeanList.add(timeZoneBean);
        }

    }

    private void loadCityList(int key) {
        switch (key) {
            case 0:
                this.mCityNameList = this.getArrayById(R.array.asia_city_name);
                this.mCityCodeList = this.getArrayById(R.array.asia_city_code);
                this.mTimeZoneList = this.getArrayById(R.array.asia_city_timezone);
                return;
            case 1:
                this.mCityNameList = this.getArrayById(R.array.afica_city_name);
                this.mCityCodeList = this.getArrayById(R.array.afica_city_code);
                this.mTimeZoneList = this.getArrayById(R.array.afica_city_timezone);
                return;
            case 2:
                this.mCityNameList = this.getArrayById(R.array.north_america_city_name);
                this.mCityCodeList = this.getArrayById(R.array.north_america_city_code);
                this.mTimeZoneList = this.getArrayById(R.array.north_america_city_timezone);
                return;
            case 3:
                this.mCityNameList = this.getArrayById(R.array.south_america_city_name);
                this.mCityCodeList = this.getArrayById(R.array.south_america_city_code);
                this.mTimeZoneList = this.getArrayById(R.array.south_america_city_timezone);
                return;
            case 4:
                this.mCityNameList = this.getArrayById(R.array.australia_city_name);
                this.mCityCodeList = this.getArrayById(R.array.australia_city_code);
                this.mTimeZoneList = this.getArrayById(R.array.australia_city_timezone);
                return;
            case 5:
                this.mCityNameList = this.getArrayById(R.array.europe_city_name);
                this.mCityCodeList = this.getArrayById(R.array.europe_city_code);
                this.mTimeZoneList = this.getArrayById(R.array.europe_city_timezone);
                return;
            default:
        }
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((TextView) this.getActivity().findViewById(R.id.title_choose_states)).setText(R.string.title_choose_city);
        View inflate = layoutInflater.inflate(R.layout.layout_word_city_time, container, false);
        ListView listView = inflate.findViewById(R.id.time_zone_list);
        this.initData();
        listView.setAdapter(new TimeZoneAdapter(getContext(), R.layout.layout_city_item, this.mTimeZoneBeanList));
        listView.setOnItemClickListener(this);
        return inflate;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TimeZoneBean timeZoneBean = (TimeZoneBean) this.mTimeZoneBeanList.get(position);
        StringBuilder builder = new StringBuilder();
        builder.append("selected city name: ");
        builder.append(timeZoneBean.getCityName());
        builder.append(" selected city code: ");
        builder.append(timeZoneBean.getCityCode());
        builder.append(" selected gmtOffSet: ");
        builder.append(timeZoneBean.getGmtOffSet());
        LogUtil.d("TimeZoneFragment", builder.toString());
        String timeZone = (String) this.mTimeZoneList.get(position);
        String cityCode = (String) this.mCityCodeList.get(position);
        Intent intent = this.getActivity().getIntent();
        if (intent == null || TextUtils.isEmpty(intent.getStringExtra("containerIndex"))) {
            WorldTimeSharedPreferencesUtil.saveWorldTimeString("time_zone_string_id", timeZone);
            WorldTimeSharedPreferencesUtil.saveWorldTimeString("time_zone_zons_id", cityCode);
        }

            this.getActivity().finish();
    }
}
