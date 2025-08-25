package org.example.linkedIn;

import java.time.Instant;

public class Notification {

    final String id;
    final String userId;
    final NotificationType type;
    final String payload;
    final Instant time = Instant.now();
    volatile boolean delivered = false;

    Notification(String id, String userId, NotificationType type, String payload) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.payload = payload;
    }
}
