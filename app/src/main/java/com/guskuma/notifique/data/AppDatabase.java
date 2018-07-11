package com.guskuma.notifique.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.guskuma.notifique.data.dao.NotificacaoDAO;
import com.guskuma.notifique.data.model.Notificacao;

@Database(entities = {Notificacao.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase{
    public abstract NotificacaoDAO notificacaoDAO();
}
