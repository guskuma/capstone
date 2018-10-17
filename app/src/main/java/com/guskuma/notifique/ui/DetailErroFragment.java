package com.guskuma.notifique.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.google.gson.Gson;
import com.guskuma.notifique.R;
import com.guskuma.notifique.commons.ConteudoErro;
import com.guskuma.notifique.data.model.Notificacao;
import org.parceler.Parcels;

public class DetailErroFragment extends AbstractDetailFragment {

    @BindView(R.id.causaErro)
    public TextView mCausaErro;

    @BindView(R.id.detalheErro)
    public TextView mDetalheErro;

    public DetailErroFragment() {
        super();
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
        View view = super.onCreateView(inflater, container, savedInstanceState);

        ConteudoErro conteudoErro = new Gson().fromJson(mNotificacao.conteudo, ConteudoErro.class);
        mCausaErro.setText(conteudoErro.causa_erro);
        mDetalheErro.setText(conteudoErro.detalhe_erro);

        return view;
    }
}