package com.education.firebase;

import com.education.client.HttpClient;
import com.education.util.JsonMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
public class Firebase {

    private static final String SCOPE1 = "https://www.googleapis.com/auth/firebase.messaging";
    private static final String SCOPE2 = "https://www.googleapis.com/auth/cloud-platform";
    private static final String FIREBASE_PUSH_URL = "https://fcm.googleapis.com/v1/projects/testtoken-e0371/messages:send";

    private static String getAccessToken() throws IOException {
        GoogleCredential googleCredential = GoogleCredential
                .fromStream(new ClassPathResource("service-account.json").getInputStream())
                .createScoped(Arrays.asList(SCOPE1, SCOPE2));
        googleCredential.refreshToken();
        return googleCredential.getAccessToken();
    }

    public static void pushFirebase(FirebaseModel firebaseModel) {
        try {
            HttpClient.getInstance()
                    .preparePost(FIREBASE_PUSH_URL)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + getAccessToken())
                    .setBody(JsonMapper.toJsonString(firebaseModel))
                    .execute()
                    .toCompletableFuture()
                    .exceptionally(t -> {
                        log.error("Firebase Push Failed userId {} exception {}", firebaseModel.getUserId(), t.getMessage());
                        return null;
                    })
                    .thenApply(response -> {
                        log.debug("Firebase push success userId {} response {} ", firebaseModel.getUserId(), response.getResponseBody());
                        return response;
                    });
        } catch (IOException e) {
            log.error("Firebase Push Failed userId {} exception {}", firebaseModel.getUserId(), e.getMessage());
        }
    }
}


