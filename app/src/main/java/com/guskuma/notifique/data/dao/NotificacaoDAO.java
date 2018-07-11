package com.guskuma.notifique.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.guskuma.notifique.data.model.Notificacao;

import java.util.List;

@Dao
public interface NotificacaoDAO {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insert(Notificacao... notificacoes);

    @Update(onConflict = OnConflictStrategy.FAIL)
    void update(Notificacao... notificacoes);

    @Delete
    void delete(Notificacao... notificacoes);

    @Query("SELECT * FROM Notificacao")
    List<Notificacao> getAll();

    @Query("SELECT * FROM Notificacao ORDER BY fixa, lida, ultima_atualizacao desc")
    List<Notificacao> getAllOrdered();


}
