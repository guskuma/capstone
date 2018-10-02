package com.guskuma.notifique.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.guskuma.notifique.R;
import com.guskuma.notifique.data.model.Notificacao;
import com.guskuma.notifique.data.support.NotificacoesAdapter;
import com.guskuma.notifique.data.support.NotificacoesLoader;
import org.parceler.Parcels;
import timber.log.Timber;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class AbstractMainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Notificacao>> {

    private Unbinder mUnbinder;
    private Loader mLoader;
    private NotificacoesAdapter mAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        Timber.plant(new Timber.DebugTree());

        mAdapter = new NotificacoesAdapter((clickedView, item) -> {
            Timber.d("Item clicado: " + item.titulo);
            Intent i = new Intent(getContext(), DetailActivity.class);
            i.putExtra(DetailActivity.ARG_NOTIFICACAO, Parcels.wrap(item));

            Pair<View, String> p1 = Pair.create(clickedView, getResources().getString(R.string.transition_name_titulo));
            Pair<View, String> p2 = Pair.create(clickedView, getResources().getString(R.string.transition_name_conteudo));
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), p1, p2);
            startActivity(i, options.toBundle());
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.gridlayout_columns)));
        mRecyclerView.setAdapter(mAdapter);

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
        mAdapter.setItems(data);
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Notificacao>> loader) {

    }
}
