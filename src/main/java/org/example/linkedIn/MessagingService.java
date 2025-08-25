package org.example.linkedIn;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class MessagingService {

    private final Stores stores;
    private final NotificationService notifications;
    private final AtomicLong seq = new AtomicLong(0L);

    MessagingService(Stores stores, NotificationService notifications) {
        this.stores = stores; this.notifications = notifications;
    }

    public void send(String fromUserId, String toUserId, String body) {
        // optional: enforce connection-only messaging
        Message m = new Message("m-" + seq.incrementAndGet(), fromUserId, toUserId, body, Instant.now());
        stores.inbox.computeIfAbsent(toUserId, k -> new CopyOnWriteArrayList<>()).add(m);
        stores.outbox.computeIfAbsent(fromUserId, k -> new CopyOnWriteArrayList<>()).add(m);
        notifications.publish(toUserId, NotificationType.MESSAGE_RECEIVED, "Msg from " + fromUserId);
    }

    public List<Message> inbox(String userId) { return stores.inbox.getOrDefault(userId, List.of()); }
    public List<Message> sent(String userId) { return stores.outbox.getOrDefault(userId, List.of()); }
}
