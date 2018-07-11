package com.guskuma.notifique.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.guskuma.notifique.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends AbstractMainActivityFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        MobileAds.initialize(getContext(), getString(R.string.admob_app_id));
        mAdView.loadAd(new AdRequest.Builder().build());

        return view;
    }
}
