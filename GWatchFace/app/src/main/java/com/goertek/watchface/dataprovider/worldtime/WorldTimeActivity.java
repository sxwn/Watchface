package com.goertek.watchface.dataprovider.worldtime;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.goertek.watchface.R;
import com.goertek.watchface.dataprovider.worldtime.fragment.ContinentFragment;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class WorldTimeActivity extends FragmentActivity {
    public static final String POSITION_KEY = "position";
    private ContinentFragment mContinentFragment;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    public WorldTimeActivity() {
    }

    protected void onCreate(Bundle var1) {
        super.onCreate(var1);
        this.setContentView(R.layout.world_time_fragment);
        this.mContinentFragment = new ContinentFragment();
        this.mFragmentManager = this.getSupportFragmentManager();
        this.mTransaction = this.mFragmentManager.beginTransaction();
        this.mTransaction.replace(R.id.world_time_frag, this.mContinentFragment);
        this.mTransaction.commit();
    }
}
