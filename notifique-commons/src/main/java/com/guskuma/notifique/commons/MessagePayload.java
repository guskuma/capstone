package com.guskuma.notifique.commons;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MessagePayload {

    public MessagePayload(){
        super();
    }

    public String tipo;

    public String titulo;

    public String conteudo_informacao;

    public ConteudoRelatorio conteudo_relatorio;

    public ConteudoErro conteudo_erro;

    public String acao;

    public String acao_conteudo;

    public Map<String, String> getData(){
        Map<String, String> data = new HashMap<>();
        data.put("tipo", tipo);
        data.put("titulo", titulo);
        data.put("conteudo_informacao", conteudo_informacao);
        data.put("conteudo_relatorio", new Gson().toJson(conteudo_relatorio));
        data.put("conteudo_erro", new Gson().toJson(conteudo_erro));
        data.put("acao", acao);
        data.put("acao_conteudo", acao_conteudo);
        return data;
    }

    public static MessagePayload getMessagePayload(Map<String,String> data){
        MessagePayload msg = new MessagePayload();
        msg.tipo = data.get("tipo");
        msg.titulo = data.get("titulo");
        msg.conteudo_informacao = data.get("conteudo_informacao");
        msg.conteudo_relatorio = new Gson().fromJson(data.get("conteudo_relatorio"), ConteudoRelatorio.class);
        msg.conteudo_erro = new Gson().fromJson(data.get("conteudo_erro"), ConteudoErro.class);
        msg.acao = data.get("acao");
        msg.acao_conteudo = data.get("acao_conteudo");
        return msg;
    }


}
