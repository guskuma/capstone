package com.guskuma.notifique.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guskuma.notifique.R;
import com.guskuma.notifique.data.model.Notificacao;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private Unbinder mUnbinder;
    private Notificacao mNotificacao;

    //@BindView(R.id.titulo) public TextView mTitulo;
    @BindView(R.id.conteudo) public TextView mConteudo;
//    @BindView(R.id.ultimaAtualizacao) public TextView mUltimaAtualizacao;
//    @BindView(R.id.colorIndicator) public View mColorIndicator;

    public DetailActivityFragment() { }

    public static DetailActivityFragment newInstance(Notificacao notificacao) {
        DetailActivityFragment fragment = new DetailActivityFragment();
        Bundle args = new Bundle();
        args.putParcelable(DetailActivity.ARG_NOTIFICACAO, Parcels.wrap(notificacao));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        Timber.plant(new Timber.DebugTree());

        mNotificacao = Parcels.unwrap(getArguments().getParcelable(DetailActivity.ARG_NOTIFICACAO));
//        mTitulo.setText(mNotificacao.titulo);
        mConteudo.setText(mNotificacao.conteudo);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
