package com.guskuma.notifique.ui;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.text.Html;
import android.widget.RemoteViews;
import com.google.gson.Gson;
import com.guskuma.notifique.R;
import com.guskuma.notifique.commons.ConteudoErro;
import com.guskuma.notifique.commons.ConteudoRelatorio;
import com.guskuma.notifique.commons.TipoNotificacao;
import com.guskuma.notifique.data.support.UltimaNotificacaoLoader;

/**
 * Implementation of App Widget functionality.
 */
public class NotifiqueAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        UltimaNotificacaoLoader loader = new UltimaNotificacaoLoader();
        loader.setListener(notificacao -> {
            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.notifique_app_widget);
            views.setTextViewText(R.id.titulo, notificacao.titulo);

            switch (notificacao.tipo) {
                case TipoNotificacao.INFORMACAO:
                    views.setTextViewText(R.id.conteudo, Html.fromHtml(notificacao.conteudo));
                    break;
                case TipoNotificacao.RELATORIO:
                    views.setTextViewText(R.id.conteudo,  new Gson().fromJson(notificacao.conteudo, ConteudoRelatorio.class).conteudo);
                    break;
                case TipoNotificacao.ERRO:
                    views.setTextViewText(R.id.conteudo, new Gson().fromJson(notificacao.conteudo, ConteudoErro.class).causa_erro);
                    break;
            }

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        });

        loader.execute(context);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

