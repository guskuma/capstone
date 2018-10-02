package com.guskuma.notifique.commons;

public class MessagePayload {

    public MessagePayload(){
        super();
    }
    public MessagePayload(String n){
        num = n;
    }

    String num;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
