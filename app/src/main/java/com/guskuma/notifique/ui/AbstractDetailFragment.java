package com.guskuma.notifique.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.guskuma.notifique.R;
import com.guskuma.notifique.commons.TipoNotificacao;
import com.guskuma.notifique.data.model.Notificacao;
import org.parceler.Parcels;
import timber.log.Timber;

public class AbstractDetailFragment extends Fragment {

    protected Unbinder mUnbinder;
    protected Notificacao mNotificacao;

    @BindView(R.id.detailTitle)
    protected TextView mTituloDetalhe;

    public AbstractDetailFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mNotificacao = Parcels.unwrap(getArguments().getParcelable(DetailActivity.ARG_NOTIFICACAO));

        View view;

        switch (mNotificacao.tipo) {
            case TipoNotificacao.INFORMACAO:
                view = inflater.inflate(R.layout.fragment_detail_informacao, container, false);
                break;
            case TipoNotificacao.RELATORIO:
                view = inflater.inflate(R.layout.fragment_detail_relatorio, container, false);
                break;
            case TipoNotificacao.ERRO:
                view = inflater.inflate(R.layout.fragment_detail_erro, container, false);
                break;
            default:
                throw new Resources.NotFoundException("Não foi encontrado layout para este tipo de notificação");
        }

        mUnbinder = ButterKnife.bind(this, view);
        Timber.plant(new Timber.DebugTree());

        mTituloDetalhe.setText(mNotificacao.titulo);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}
