package com.guskuma.notifique.service;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.LocalBroadcastManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.guskuma.notifique.R;
import com.guskuma.notifique.commons.MessagePayload;
import com.guskuma.notifique.commons.TipoNotificacao;
import com.guskuma.notifique.data.AppDatabase;
import com.guskuma.notifique.data.AppDatabaseHelper;
import com.guskuma.notifique.data.model.Notificacao;
import com.guskuma.notifique.ui.DetailActivity;
import org.parceler.Parcels;
import timber.log.Timber;

import java.util.Date;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String NOTIFICATION_CHANNEL_ID = "654198498";
    static final int NOTIFICATION_ID = 654654694;
    static final int PENDING_INTENT_REQUEST_CODE = 34252342;
    public static final String BROADCAST_NOVA_NOTIFICACAO = "EHEHEHE";
    public static final String BROADCAST_NOVA_NOTIFICACAO_ENTIDADE = "BROADCAST_NOVA_NOTIFICACAO_ENTIDADE";

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        AppDatabase mDb = AppDatabaseHelper.getDb(this.getApplicationContext());

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Timber.d("From: %s", remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Timber.d("Message data payload: %s", remoteMessage.getData());

            MessagePayload msg = MessagePayload.getMessagePayload(remoteMessage.getData());

            Notificacao notificacao = new Notificacao();
            notificacao.remote_id = new Random().nextInt();
            notificacao.tipo = Integer.valueOf(msg.tipo);
            notificacao.titulo = msg.titulo;
            notificacao.acao = Integer.valueOf(msg.acao);
            notificacao.acao_conteudo = msg.acao_conteudo;
            notificacao.lida = false;
            notificacao.fixa = false;
            notificacao.ultima_atualizacao = new Date();

            switch (notificacao.tipo){
                case TipoNotificacao.INFORMACAO:
                    notificacao.conteudo = msg.conteudo_informacao;
                    break;
                case TipoNotificacao.RELATORIO:
                    notificacao.conteudo = new Gson().toJson(msg.conteudo_relatorio);
                    break;
                case TipoNotificacao.ERRO:
                    notificacao.conteudo = msg.conteudo_erro;
                    break;
            }

            mDb.notificacaoDAO().insert(notificacao);

            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.ARG_NOTIFICACAO, Parcels.wrap(notificacao));

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this).addNextIntentWithParentStack(intent);

            PendingIntent pendingIntent = stackBuilder.getPendingIntent(PENDING_INTENT_REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(TipoNotificacao.getDescricao(Integer.valueOf(msg.tipo)))
                    .setContentText(msg.titulo)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent);
//                    .setAutoCancel(true);

            NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, mBuilder.build());

            Intent broadcastIntent = new Intent(BROADCAST_NOVA_NOTIFICACAO);
            broadcastIntent.putExtra(BROADCAST_NOVA_NOTIFICACAO_ENTIDADE, Parcels.wrap(notificacao));
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(broadcastIntent);

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Timber.d("Message Notification Body: %s", remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
}
