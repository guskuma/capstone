package com.guskuma.notifique.commons;

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


}
