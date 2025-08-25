package org.example.linkedIn;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class NotificationService {

    private final Map<String, List<NotificationListener>> listeners = new ConcurrentHashMap<>();
    private final ExecutorService exec = Executors.newFixedThreadPool(4);
    private final AtomicLong seq = new AtomicLong(0L);

    public void subscribe(String userId, NotificationListener listener) {
        listeners.computeIfAbsent(userId, k -> new CopyOnWriteArrayList<>()).add(listener);
    }

    public Notification publish(String userId, NotificationType type, String payload) {
        Notification n = new Notification("n-" + seq.incrementAndGet(), userId, type, payload);
        List<NotificationListener> subs = listeners.getOrDefault(userId, List.of());
        for (NotificationListener l : subs) {
            exec.submit(() -> {
                l.onNotification(n);
                n.delivered = true;
            });
        }
        return n;
    }

    public void shutdown() { exec.shutdown(); }
}
