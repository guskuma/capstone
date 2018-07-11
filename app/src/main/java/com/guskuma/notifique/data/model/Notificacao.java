package com.guskuma.notifique.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(indices =
        {
                @Index(unique = true, value = {"remote_id"}),
                @Index(value = {"ultima_atualizacao"})
        })
public class Notificacao {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public long remote_id;

    public int tipo;

    public String titulo;

    public String conteudo;

    public int acao;

    public String acao_conteudo;

    public boolean lida;

    public boolean fixa;

    public Date ultima_atualizacao;

}
