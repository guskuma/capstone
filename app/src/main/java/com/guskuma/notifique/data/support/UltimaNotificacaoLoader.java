package com.guskuma.notifique.data.support;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.guskuma.notifique.data.AppDatabase;
import com.guskuma.notifique.data.AppDatabaseHelper;
import com.guskuma.notifique.data.model.Notificacao;

public class UltimaNotificacaoLoader extends AsyncTask<Context, Void, Notificacao> {

    public void setListener(Listener mListener) {
        this.mListener = mListener;
    }

    Listener mListener;

    @Override
    protected Notificacao doInBackground(@NonNull Context... contexts) {
        AppDatabase mDb = AppDatabaseHelper.getDb(contexts[0]);
        return mDb.notificacaoDAO().getLast();
    }

    @Override
    protected void onPostExecute(Notificacao notificacao) {
        mListener.onResult(notificacao);
    }

    public interface Listener {
        void onResult(Notificacao notificacao);
    }

}
