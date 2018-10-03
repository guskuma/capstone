package com.guskuma.notifique.commons;

import java.util.HashMap;
import java.util.Map;

public class MessagePayload {

    public MessagePayload(){
        super();
    }

    public String tipo;

    public String titulo;

    public String conteudo;

    public String conteudo_tipo;

    public String acao;

    public String acao_conteudo;

    public Map<String, String> getData(){
        Map<String, String> data = new HashMap<>();
        data.put("tipo", tipo);
        data.put("titulo", titulo);
        data.put("conteudo", conteudo);
        data.put("conteudo_tipo", conteudo_tipo);
        data.put("acao", acao);
        data.put("acao_conteudo", acao_conteudo);
        return data;
    }

    public static MessagePayload getMessagePayload(Map<String,String> data){
        MessagePayload msg = new MessagePayload();
        msg.tipo = data.get("tipo");
        msg.titulo = data.get("titulo");
        msg.conteudo = data.get("conteudo");
        msg.conteudo_tipo = data.get("conteudo_tipo");
        msg.acao = data.get("acao");
        msg.acao_conteudo = data.get("acao_conteudo");
        return msg;
    }


}
