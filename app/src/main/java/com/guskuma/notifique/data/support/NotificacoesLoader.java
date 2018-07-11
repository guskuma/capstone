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

        if(mDb.notificacaoDAO().getAll().size() == 0)
            for(int i =0; i < 10; i++) {
                mDb.notificacaoDAO().insert(AppDatabaseHelper.createNotificacao(i));
            }

        return mDb.notificacaoDAO().getAll();
    }

}
