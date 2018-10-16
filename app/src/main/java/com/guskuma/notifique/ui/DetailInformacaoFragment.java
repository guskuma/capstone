package com.guskuma.notifique.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.guskuma.notifique.R;
import com.guskuma.notifique.data.model.Notificacao;
import org.parceler.Parcels;
import timber.log.Timber;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailInformacaoFragment extends Fragment {

    private Unbinder mUnbinder;
    private Notificacao mNotificacao;

    @BindView(R.id.conteudo) public TextView mConteudo;

    @BindView(R.id.detailTitle)
    TextView mTituloDetalhe;

    public DetailInformacaoFragment() { }

    public static DetailInformacaoFragment newInstance(Notificacao notificacao, boolean setTitle) {
        DetailInformacaoFragment fragment = new DetailInformacaoFragment();
        Bundle args = new Bundle();
        args.putParcelable(DetailActivity.ARG_NOTIFICACAO, Parcels.wrap(notificacao));
        fragment.setArguments(args);
        args.putBoolean(DetailActivity.ARG_SET_TITLE, setTitle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_informacao, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        Timber.plant(new Timber.DebugTree());

        mNotificacao = Parcels.unwrap(getArguments().getParcelable(DetailActivity.ARG_NOTIFICACAO));

        if(getArguments().getBoolean(DetailActivity.ARG_SET_TITLE)) {
            mTituloDetalhe.setText(mNotificacao.titulo);
        } else {
            mTituloDetalhe.setVisibility(View.GONE);
        }


        mConteudo.setText(Html.fromHtml(mNotificacao.conteudo));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
