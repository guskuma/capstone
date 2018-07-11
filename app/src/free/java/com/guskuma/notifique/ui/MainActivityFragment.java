package com.guskuma.notifique.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.guskuma.notifique.R;
import com.guskuma.notifique.data.model.Notificacao;
import com.guskuma.notifique.data.support.NotificacoesAdapter;
import com.guskuma.notifique.data.support.NotificacoesLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Notificacao>>{

    private Unbinder mUnbinder;
    private Loader mLoader;
    private NotificacoesAdapter mAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.adView) AdView mAdView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        Timber.plant(new Timber.DebugTree());

        mAdapter = new NotificacoesAdapter(item -> Timber.d("Item clicado: " + item.titulo));

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.gridlayout_columns)));
        mRecyclerView.setAdapter(mAdapter);

        MobileAds.initialize(getContext(), getString(R.string.admob_app_id));
        mAdView.loadAd(new AdRequest.Builder().build());

        mLoader = getLoaderManager().initLoader(NotificacoesLoader.ID, null, this);
        loadNotificacoes();

        return view;
    }

    private void loadNotificacoes() {
        mLoader.forceLoad();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


    @NonNull
    @Override
    public Loader<List<Notificacao>> onCreateLoader(int id, @Nullable Bundle args) {
        return new NotificacoesLoader(getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Notificacao>> loader, List<Notificacao> data) {
        mAdapter.addItems(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Notificacao>> loader) {

    }
}
