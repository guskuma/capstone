package com.guskuma.notifique.data.model;

import android.content.Intent;
import android.net.Uri;

public class TipoAcao {

    public static final int ABRIR_LINK = 0;
    public static final int COMPARTILHAR = 1;
    public static final int LIGAR = 2;

    public static Intent getActionIntent(int tipoAcao, String extra){
        Intent intent;
        switch (tipoAcao){
            case LIGAR:
            case COMPARTILHAR:
            case ABRIR_LINK:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + extra));
                break;
            default:
                return null;
        }
        return intent;
    }

}
