package com.guskuma.notifique.data;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import com.guskuma.notifique.R;
import com.guskuma.notifique.commons.TipoAcao;
import com.guskuma.notifique.commons.TipoNotificacao;

public class NotifiqueHelper {

    public static int getColor(Context context, final int tipoNotificacao) {
        switch (tipoNotificacao) {
            case TipoNotificacao.INFORMACAO:
                return context.getResources().getColor(R.color.notificacao_tipo_informacao_primaryDark);
            case TipoNotificacao.RELATORIO:
                return context.getResources().getColor(R.color.notificacao_tipo_relatorio_primaryDark);
            case TipoNotificacao.ERRO:
                return context.getResources().getColor(R.color.notificacao_tipo_erro_primaryDark);
            default:
                return context.getResources().getColor(R.color.colorPrimaryDark);
        }
    }

    public static int getLightColor(Context context, final int tipoNotificacao) {
        switch (tipoNotificacao) {
            case TipoNotificacao.INFORMACAO:
                return context.getResources().getColor(R.color.notificacao_tipo_informacao_primary);
            case TipoNotificacao.RELATORIO:
                return context.getResources().getColor(R.color.notificacao_tipo_relatorio_primary);
            case TipoNotificacao.ERRO:
                return context.getResources().getColor(R.color.notificacao_tipo_erro_primary);
            default:
                return context.getResources().getColor(R.color.colorPrimary);
        }
    }

    public static Intent getActionIntent(int tipoAcao, String extra){
        Intent sendIntent;
        switch (tipoAcao){
            case TipoAcao.LIGAR:
                sendIntent = new Intent(Intent.ACTION_DIAL);
                sendIntent.setData(Uri.parse("tel:" + extra));
                break;
            case TipoAcao.COMPARTILHAR:
                sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, extra);
                sendIntent.setType("text/plain");
                break;
            case TipoAcao.ABRIR_LINK:
                sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(extra));
                break;
            default:
                return null;
        }
        return sendIntent;
    }

    public static Drawable getDrawable(Context context, final int tipoAcao) {
        switch (tipoAcao) {
            case TipoAcao.ABRIR_LINK:
                return ContextCompat.getDrawable(context, android.R.drawable.ic_dialog_info);
            case TipoAcao.COMPARTILHAR:
                return ContextCompat.getDrawable(context, android.R.drawable.ic_menu_share);
            case TipoAcao.LIGAR:
                return ContextCompat.getDrawable(context, android.R.drawable.ic_menu_call);
            default:
                return ContextCompat.getDrawable(context, android.R.drawable.ic_media_play);
        }
    }

}
