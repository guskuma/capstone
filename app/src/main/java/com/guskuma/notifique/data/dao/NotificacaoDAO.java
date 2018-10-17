package com.guskuma.notifique.data.dao;

import android.arch.persistence.room.*;
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

    @Query("SELECT * FROM Notificacao ORDER BY ultima_atualizacao desc LIMIT 1")
    Notificacao getLast();


}
