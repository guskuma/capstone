package com.guskuma.notifique.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.guskuma.notifique.R;
import com.guskuma.notifique.commons.TipoNotificacao;
import com.guskuma.notifique.data.NotifiqueHelper;
import com.guskuma.notifique.data.model.Notificacao;
import org.parceler.Parcels;
import timber.log.Timber;

public class DetailActivity extends AppCompatActivity {

    public static final String ARG_NOTIFICACAO = "NotificacaoEntity";

    @BindView(R.id.titulo) public TextView mTitulo;
    @BindView(R.id.fab) FloatingActionButton mFab;

    private Notificacao mNotificacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mNotificacao = Parcels.unwrap(getIntent().getParcelableExtra(ARG_NOTIFICACAO));

        Fragment fragment;

        //Verifica o tipo da notificação e aí gera o fragment adequado
        switch (mNotificacao.tipo){
            case TipoNotificacao.INFORMACAO:
                fragment = DetailActivityFragment.newInstance(mNotificacao);
//                setTheme(R.style.AppThemeInformacao);
                break;
            case TipoNotificacao.RELATORIO:
                fragment = DetailActivityFragment.newInstance(mNotificacao);
//                setTheme(R.style.AppThemeRelatorio);
                break;
            case TipoNotificacao.ERRO:
                fragment = DetailActivityFragment.newInstance(mNotificacao);
//                setTheme(R.style.AppThemeErro);
                break;
            default:
                return;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Timber.plant(new Timber.DebugTree());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mTitulo.setText(mNotificacao.titulo);

        if(fragment != null){
            getSupportFragmentManager().beginTransaction().add(R.id.detailFragmentPlaceHolder, fragment).commit();
        }

        mFab.setOnClickListener(view -> {
            Intent intent = NotifiqueHelper.getActionIntent(mNotificacao.acao, mNotificacao.acao_conteudo);
            startActivity(Intent.createChooser(intent, getResources().getText(R.string.intent_chooser)));
        });
        mFab.setImageDrawable(NotifiqueHelper.getDrawable(this, mNotificacao.acao));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



}
