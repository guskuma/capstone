package com.guskuma.notifique.data.support;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import com.guskuma.notifique.data.AppDatabase;
import com.guskuma.notifique.data.AppDatabaseHelper;
import com.guskuma.notifique.data.model.Notificacao;

import java.util.List;

public class NotificacoesLoader extends AsyncTaskLoader<List<Notificacao>> {

    public static final int ID = 89345873;

    public NotificacoesLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    public List<Notificacao> loadInBackground() {
        AppDatabase mDb = AppDatabaseHelper.getDbget(getContext());

        List<Notificacao> notificacoes = mDb.notificacaoDAO().getAll();

        if(notificacoes.size() > 0){
            mDb.notificacaoDAO().delete(notificacoes.toArray(new Notificacao[notificacoes.size()]));
        }

        for(int i =0; i < 10; i++) {
            mDb.notificacaoDAO().insert(AppDatabaseHelper.createRandomNotificacao(i));
        }

        return mDb.notificacaoDAO().getAllOrdered();
    }

}
