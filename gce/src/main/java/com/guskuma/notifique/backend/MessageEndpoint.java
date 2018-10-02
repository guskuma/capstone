package com.guskuma.notifique.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.guskuma.notifique.commons.MessagePayload;

import java.io.FileInputStream;

@Api(
        name = "messages",
        version = "v1",
        namespace =
        @ApiNamespace(
                ownerDomain = "backend.notifique.guskuma.com",
                ownerName = "backend.notifique.guskuma.com",
                packagePath = ""
        )
)
public class MessageEndpoint {

    public MessageEndpoint() {
        super();

        try {
            FileInputStream serviceAccount = new FileInputStream("WEB-INF/serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @ApiMethod(name = "sendToTopic")
    public MessagePayload sendToTopic(MessagePayload messagePayload) {

        messagePayload.setNum(messagePayload.getNum() + ", Agora foi!");

        String topic = "all";

        // See documentation on defining a messagePayload payload.
        Message msg = Message.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .setTopic(topic)
                .build();

        // Send a messagePayload to the devices subscribed to the provided topic.
        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(msg);

            // Response is a messagePayload ID string.
            System.out.println("Successfully sent messagePayload: " + response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            System.out.println("Error while sending message: " + e.getMessage());
        }

        return messagePayload;
    }

    @ApiMethod(name = "message", path = "{n}")
    public MessagePayload getMessage(@Named("n") String n) {
        return new MessagePayload(n);
    }

    @ApiMethod(name = "message1")
    public MessagePayload getMessage1() {
        return new MessagePayload("Oláááá");
    }

}
