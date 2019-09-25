package com.education.firebase.utils;

import com.education.firebase.FirebaseMessage;
import com.education.firebase.FirebaseModel;
import com.education.firebase.FirebaseNotification;
import com.education.model.NotificationMessage;

public class FirebaseHelper {

    public static FirebaseModel buildFireBaseMessege(NotificationMessage notificationMessage) {
        return FirebaseModel.builder()
                .message(FirebaseMessage.builder()
                        .token(notificationMessage.getTo())
                        .notification(FirebaseNotification.builder()
                                .title(notificationMessage.getTitle())
                                .body(notificationMessage.getPayload())
                                .build())
                        .build())
                .build();
    }
}
