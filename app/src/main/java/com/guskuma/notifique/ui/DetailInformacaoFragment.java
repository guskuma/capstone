package com.guskuma.notifique.ui;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.guskuma.notifique.R;
import com.guskuma.notifique.data.model.Notificacao;
import org.parceler.Parcels;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailInformacaoFragment extends AbstractDetailFragment {

    @BindView(R.id.conteudo) public TextView mConteudo;

    public DetailInformacaoFragment() { super(); }

    public static DetailInformacaoFragment newInstance(Notificacao notificacao) {
        DetailInformacaoFragment fragment = new DetailInformacaoFragment();
        Bundle args = new Bundle();
        args.putParcelable(DetailActivity.ARG_NOTIFICACAO, Parcels.wrap(notificacao));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = super.onCreateView(inflater,container, savedInstanceState);

        mConteudo.setText(Html.fromHtml(mNotificacao.conteudo));

        return view;
    }
}
