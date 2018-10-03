package com.guskuma.notifique.service;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.guskuma.notifique.R;
import com.guskuma.notifique.commons.MessagePayload;
import com.guskuma.notifique.commons.TipoNotificacao;
import com.guskuma.notifique.data.AppDatabase;
import com.guskuma.notifique.data.AppDatabaseHelper;
import com.guskuma.notifique.data.model.Notificacao;
import timber.log.Timber;

import java.util.Date;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String NOTIFICATION_CHANNEL_ID = "654198498";
    static final int NOTIFICATION_ID = 654654694;

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

            Notificacao n = new Notificacao();
            n.remote_id = new Random().nextInt();
            n.tipo = Integer.valueOf(msg.tipo);
            n.titulo = msg.titulo;
            n.conteudo = msg.conteudo;
            n.acao = Integer.valueOf(msg.acao);
            n.acao_conteudo = msg.acao_conteudo;
            n.lida = false;
            n.fixa = false;
            n.ultima_atualizacao = new Date();

            mDb.notificacaoDAO().insert(n);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(TipoNotificacao.getDescricao(Integer.valueOf(msg.tipo)))
                    .setContentText(msg.titulo)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, mBuilder.build());

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Timber.d("Message Notification Body: %s", remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
}
