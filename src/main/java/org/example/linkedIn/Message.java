package org.example.linkedIn;

import java.time.Instant;

public class Message {

    final String id;
    final String fromUserId;
    final String toUserId;
    final String body;
    final Instant time;

    Message(String id, String fromUserId, String toUserId, String body, Instant time) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.body = body;
        this.time = time;
    }
}
