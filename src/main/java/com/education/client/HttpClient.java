package com.education.client;

import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncHttpClient;

import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.asynchttpclient.Dsl.config;

@Slf4j //TODO
public class HttpClient {

    private static AsyncHttpClient client;


    public static AsyncHttpClient getInstance() {

        if (client == null) {
            config()
                    .setPooledConnectionIdleTimeout(1000)
                    .setConnectionTtl(500)
                    .setConnectTimeout(1000)
                    .setRequestTimeout(60000)
                    .setKeepAlive(true)
                    .setMaxConnections(200)
                    .setMaxConnectionsPerHost(100);
            client = asyncHttpClient(config());
        }
        return client;
    }
}


