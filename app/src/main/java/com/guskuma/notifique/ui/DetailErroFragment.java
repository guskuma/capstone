package com.guskuma.notifique.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.google.gson.Gson;
import com.guskuma.notifique.R;
import com.guskuma.notifique.commons.ConteudoErro;
import com.guskuma.notifique.data.model.Notificacao;
import org.parceler.Parcels;
import timber.log.Timber;

public class DetailErroFragment extends Fragment {

    private Unbinder mUnbinder;
    private Notificacao mNotificacao;

    @BindView(R.id.causaErro)
    public TextView mCausaErro;
    @BindView(R.id.detalheErro)
    public TextView mDetalheErro;

    public DetailErroFragment() {
    }

    public static DetailErroFragment newInstance(Notificacao notificacao) {
        DetailErroFragment fragment = new DetailErroFragment();
        Bundle args = new Bundle();
        args.putParcelable(DetailActivity.ARG_NOTIFICACAO, Parcels.wrap(notificacao));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_erro, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        Timber.plant(new Timber.DebugTree());

        mNotificacao = Parcels.unwrap(getArguments().getParcelable(DetailActivity.ARG_NOTIFICACAO));

        ConteudoErro conteudoErro = new Gson().fromJson(mNotificacao.conteudo, ConteudoErro.class);

        mCausaErro.setText(conteudoErro.causa_erro);
        mDetalheErro.setText(conteudoErro.detalhe_erro);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}