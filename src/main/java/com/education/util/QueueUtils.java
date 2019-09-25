package com.education.util;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

@Slf4j
public class QueueUtils {

    public static final String FOLLOW_QUEUE = "follow";
    public static final String EXCHANGE_NAME = "follow_exchange";
    public static final String ROUTING_KEY = "follow_routing_key";

    private static QueueUtils instance;

    private Channel publishChannel;
    private Channel followChannel;
    private Connection connection;
    private ConnectionFactory factory;

    private QueueUtils() {
    }

    public static QueueUtils getInstance() {
        if (instance == null) {
            instance = new QueueUtils();
        }
        return instance;
    }

    private ConnectionFactory createFactory() {
        if (factory == null) {
            factory = new ConnectionFactory();
            factory.setHost("rabbitmq");
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setAutomaticRecoveryEnabled(true);
            factory.setConnectionTimeout(60000);
            factory.setNetworkRecoveryInterval(65000);
        }

        return factory;
    }

    public Connection createConnection() throws IOException, TimeoutException {
        if (connection == null || !connection.isOpen()) {
            ExecutorService executor = Executors.newFixedThreadPool(5);
            connection = createFactory().newConnection(executor);
        }
        return connection;
    }

    private Channel getPublishChannel() throws IOException, TimeoutException {

        if (publishChannel == null || !publishChannel.isOpen()) {
            publishChannel = createConnection().createChannel();
            exchangeDeclare(publishChannel);
        }
        return publishChannel;
    }

    private void exchangeDeclare(Channel channel) throws IOException {
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        queueDeclare(channel);

    }

    private void queueDeclare(Channel channel) throws IOException {
        channel.queueDeclare(FOLLOW_QUEUE, true, false, false, null);
        queueBind(channel);
    }

    private void queueBind(Channel channel) throws IOException {
        channel.queueBind(FOLLOW_QUEUE, EXCHANGE_NAME, ROUTING_KEY);
    }

    public Channel getFollowConsumerChannel() throws IOException, TimeoutException {

        if (followChannel == null || !followChannel.isOpen()) {
            followChannel = createConnection().createChannel();
            exchangeDeclare(followChannel);
        }
        return followChannel;
    }

    public void publish(String exchangeName, String routingKey, Object message) throws IOException, TimeoutException {

        final byte[] messageBytes = JsonMapper.toJsonString(message).getBytes();
        getPublishChannel().basicPublish(exchangeName, routingKey, null, messageBytes);

    }

}
