package com.education.rabbitmq.producer;

import com.education.firebase.utils.FirebaseHelper;
import com.education.model.NotificationMessage;
import com.education.util.QueueUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
public class QueueProducer {

    public static void sendFollowQueue(NotificationMessage notificationMessage) {

        try {
            QueueUtils.getInstance().publish(QueueUtils.EXCHANGE_NAME, QueueUtils.ROUTING_KEY, FirebaseHelper.buildFireBaseMessege(notificationMessage));
            log.debug("Queue '{}' payload '{}' to '{}'", QueueUtils.ROUTING_KEY, notificationMessage.getPayload(), notificationMessage.getTo());
        } catch (IOException | TimeoutException e) {
            log.error("Send to queue fail e --> {}", e.getMessage());
        }
    }
}
