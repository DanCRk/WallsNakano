package com.futurefix.wallsnakano.adaptadores;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.futurefix.wallsnakano.fragments.CategoriasFragment;
import com.futurefix.wallsnakano.fragments.IchikaFragment;
import com.futurefix.wallsnakano.fragments.ItsukiFragment;
import com.futurefix.wallsnakano.fragments.MikuFragment;
import com.futurefix.wallsnakano.fragments.NinoFragment;
import com.futurefix.wallsnakano.fragments.YotsubaFragment;

import org.jetbrains.annotations.NotNull;

public class PagerAdapter extends FragmentPagerAdapter {
    int numTabs;

    public PagerAdapter(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numTabs = behavior;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CategoriasFragment();
            case 1:
                return new NinoFragment();
            case 2:
                return new ItsukiFragment();
            case 3:
                return new MikuFragment();
            case 4:
                return new YotsubaFragment();
            case 5:
                return new IchikaFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
