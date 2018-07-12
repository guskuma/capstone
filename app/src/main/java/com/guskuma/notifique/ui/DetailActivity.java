package com.guskuma.notifique.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.guskuma.notifique.R;
import com.guskuma.notifique.data.model.Notificacao;
import com.guskuma.notifique.data.model.TipoNotificacao;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class DetailActivity extends AppCompatActivity {

    public static final String ARG_NOTIFICACAO = "NotificacaoEntity";

    @BindView(R.id.titulo) public TextView mTitulo;
    @BindView(R.id.fab) FloatingActionButton fab;

    private Notificacao mNotificacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Timber.plant(new Timber.DebugTree());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mNotificacao = Parcels.unwrap(getIntent().getParcelableExtra(ARG_NOTIFICACAO));

        mTitulo.setText(mNotificacao.titulo);

        Fragment fragment;

        //Verifica o tipo da notificação e aí gera o fragment adequado
        switch (mNotificacao.tipo){
            case TipoNotificacao.INFORMACAO:
                fragment = DetailActivityFragment.newInstance(mNotificacao);
                break;
            case TipoNotificacao.RELATORIO:
            case TipoNotificacao.ERRO:
                fragment = DetailActivityFragment.newInstance(mNotificacao);
                break;
            default:
                return;
        }

        if(fragment != null){
            getSupportFragmentManager().beginTransaction().add(R.id.detailFragmentPlaceHolder, fragment).commit();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



}
