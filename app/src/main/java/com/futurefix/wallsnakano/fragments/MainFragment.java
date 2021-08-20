package com.futurefix.wallsnakano.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futurefix.wallsnakano.R;
import com.futurefix.wallsnakano.adaptadores.PagerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class MainFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    PagerAdapter pagerAdapter;
    AdView banner;

    public MainFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        banner = view.findViewById(R.id.adViewBannerTabs);

        //Anuncios
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);

        pagerAdapter =new PagerAdapter(getChildFragmentManager(), getLifecycle());

        pagerAdapter.addFragment(new CategoriasFragment());  //0
        pagerAdapter.addFragment(new NinoFragment());
        pagerAdapter.addFragment(new ItsukiFragment());
        pagerAdapter.addFragment(new MikuFragment());
        pagerAdapter.addFragment(new YotsubaFragment());
        pagerAdapter.addFragment(new IchikaFragment());
        viewPager.setAdapter(pagerAdapter);
        final String [] tabss = new String[]{"CATEGORIAS","NINO","ITSUKI","MIKU","YOTSUBA","ICHIKA"};
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) ->  tab.setText(tabss[position])).attach();
        return  view;
    }

    @Override
    public void onPause() {
        super.onPause();
        pagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        pagerAdapter.notifyDataSetChanged();
    }
}