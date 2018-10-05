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

public class DetailRelatorioFragment extends Fragment {

    private Unbinder mUnbinder;
    private Notificacao mNotificacao;

    @BindView(R.id.conteudo)
    public TextView mConteudo;

    public DetailRelatorioFragment() {
    }

    public static DetailRelatorioFragment newInstance(Notificacao notificacao) {
        DetailRelatorioFragment fragment = new DetailRelatorioFragment();
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
        mConteudo.setText(Html.fromHtml(mNotificacao.conteudo));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}