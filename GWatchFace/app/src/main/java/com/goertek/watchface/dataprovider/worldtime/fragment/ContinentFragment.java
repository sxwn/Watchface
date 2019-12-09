package com.goertek.watchface.dataprovider.worldtime.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.goertek.watchface.R;
import com.goertek.watchface.dataprovider.worldtime.adapter.ContinentAdapter;
import com.goertek.watchface.dataprovider.worldtime.bean.ContinentBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class ContinentFragment extends Fragment implements AdapterView.OnItemClickListener {
    public ContinentFragment() {
    }

    private List<ContinentBean> getContinentBeans() {
        int recourseListSize = 0;
        List<ContinentBean> continentBeanList = new ArrayList(0);
        List<String> recourcesList = Arrays.asList(this.getResources().getStringArray(R.array.continent_name));

        for (TypedArray typedArray = this.getResources().obtainTypedArray(R.array.continent_icon); recourseListSize < recourcesList.size(); ++recourseListSize) {
            continentBeanList.add(new ContinentBean(recourcesList.get(recourseListSize), typedArray.getDrawable(recourseListSize)));
        }

        return continentBeanList;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((TextView) this.getActivity().findViewById(R.id.title_choose_states)).setText(R.string.title_choose_states);
        View inflate = layoutInflater.inflate(R.layout.layout_world_time, container, false);
        List<ContinentBean> continentBeans = this.getContinentBeans();
        ContinentAdapter continentAdapter = new ContinentAdapter(this.getContext(), R.layout.layout_continent_item, continentBeans);
        ListView listView = inflate.findViewById(R.id.world_time_list);
        listView.setAdapter(continentAdapter);
        listView.setOnItemClickListener(this);
        return inflate;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        TimeZoneFragment timeZoneFragment = new TimeZoneFragment();
        timeZoneFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.world_time_frag, timeZoneFragment);
        fragmentTransaction.commit();
    }
}
