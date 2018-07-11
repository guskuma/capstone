package com.guskuma.notifique.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.guskuma.notifique.data.model.Notificacao;
import com.guskuma.notifique.data.model.TipoAcao;
import com.guskuma.notifique.data.model.TipoNotificacao;

import java.util.Date;

public class AppDatabaseHelper {

    private static AppDatabase mDb = null;

    public static AppDatabase getDbget(Context context){

        if(mDb == null) {
            mDb = Room.databaseBuilder(context,
                    AppDatabase.class, "notifique-database").build();
        }

        return mDb;
    }

    public static Notificacao createNotificacao(int id) {
        Notificacao n = new Notificacao();
        n.remote_id = id;
        n.tipo = TipoNotificacao.INFORMACAO;
        n.titulo = "Testando "+ id;
        n.conteudo = "Conteudo "+ id;
        n.acao = TipoAcao.ABRIR_LINK;
        n.acao_conteudo = "http://www.notifique.com";
        n.lida = false;
        n.fixa = false;
        n.ultima_atualizacao = new Date();
        return n;
    }

}
