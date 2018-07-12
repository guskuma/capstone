package com.guskuma.notifique.data.model;

import android.content.Context;

import com.guskuma.notifique.R;

public class TipoNotificacao {

    public static final int INFORMACAO = 0;
    public static final int RELATORIO = 1;
    public static final int ERRO = 2;

    public static String getDescricao(final int tipoNotificacao){
        switch (tipoNotificacao){
            case INFORMACAO:
                return "Informação";
            case RELATORIO:
                return "Relatório";
            case ERRO:
                return "Erro";
            default:
                return "Não reconhecido";
        }
    }

    public static int getColor(Context context, final int tipoNotificacao) {
        switch (tipoNotificacao) {
            case TipoNotificacao.INFORMACAO:
                return context.getResources().getColor(R.color.notificacao_tipo_informacao);
            case TipoNotificacao.RELATORIO:
                return context.getResources().getColor(R.color.notificacao_tipo_relatorio);
            case TipoNotificacao.ERRO:
                return context.getResources().getColor(R.color.notificacao_tipo_erro);
            default:
                return context.getResources().getColor(R.color.notificacao_tipo_default);
        }
    }

}
