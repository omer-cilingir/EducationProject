package com.education.rabbitmq.consumer;

import com.education.firebase.Firebase;
import com.education.firebase.FirebaseModel;
import com.education.util.JsonMapper;
import com.education.util.QueueUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Slf4j
public class FollowQueueConsumer implements Runnable {


    private void consume() throws IOException, TimeoutException {

        Channel channel = QueueUtils.getInstance().getFollowConsumerChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            log.info("Received " + message);
            final FirebaseModel firebaseModel = JsonMapper.toObject(message, FirebaseModel.class);
            Firebase.pushFirebase(firebaseModel);
        };
        final CancelCallback cancelCallback = consumerTag -> log.info("{} queue consuming cancelled consumerTag {}", QueueUtils.FOLLOW_QUEUE, consumerTag);

        channel.basicConsume(QueueUtils.FOLLOW_QUEUE, true, deliverCallback, cancelCallback);
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (IOException | TimeoutException e) {
            log.error("{} queue consume failed exception {}", QueueUtils.FOLLOW_QUEUE, e.getMessage());
        }
    }
}