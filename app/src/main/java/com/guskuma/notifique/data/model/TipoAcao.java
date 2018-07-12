package com.guskuma.notifique.data.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;

public class TipoAcao {

    public static final int ABRIR_LINK = 0;
    public static final int COMPARTILHAR = 1;
    public static final int LIGAR = 2;

    public static Intent getActionIntent(int tipoAcao, String extra){
        Intent sendIntent;
        switch (tipoAcao){
            case LIGAR:
                sendIntent = new Intent(Intent.ACTION_DIAL);
                sendIntent.setData(Uri.parse("tel:" + extra));
                break;
            case COMPARTILHAR:
                sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, extra);
                sendIntent.setType("text/plain");
                break;
            case ABRIR_LINK:
                sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(extra));
                break;
            default:
                return null;
        }
        return sendIntent;
    }

    public static Drawable getDrawable(Context context, final int tipoAcao) {
        switch (tipoAcao) {
            case ABRIR_LINK:
                return ContextCompat.getDrawable(context, android.R.drawable.ic_dialog_info);
            case COMPARTILHAR:
                return ContextCompat.getDrawable(context, android.R.drawable.ic_menu_share);
            case LIGAR:
                return ContextCompat.getDrawable(context, android.R.drawable.ic_menu_call);
            default:
                return ContextCompat.getDrawable(context, android.R.drawable.ic_media_play);
        }
    }

}
