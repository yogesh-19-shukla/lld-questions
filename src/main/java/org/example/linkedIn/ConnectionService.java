package org.example.linkedIn;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionService {

    private final Stores stores;
    private final NotificationService notifications;

    ConnectionService(Stores stores, NotificationService notifications) {
        this.stores = stores;
        this.notifications = notifications;
    }

    public void sendRequest(String fromUserId, String toUserId) {
        if (fromUserId.equals(toUserId)) throw new IllegalArgumentException("self connect not allowed");
        stores.pendingOutgoing.computeIfAbsent(fromUserId, k -> ConcurrentHashMap.newKeySet()).add(toUserId);
        stores.pendingIncoming.computeIfAbsent(toUserId, k -> ConcurrentHashMap.newKeySet()).add(fromUserId);
        notifications.publish(toUserId, NotificationType.CONNECTION_REQUEST, "Request from " + fromUserId);
    }

    public void respond(String toUserId, String fromUserId, boolean accept) {
        stores.pendingIncoming.getOrDefault(toUserId, Set.of()).remove(fromUserId);
        stores.pendingOutgoing.getOrDefault(fromUserId, Set.of()).remove(toUserId);
        if (accept) {
            stores.connections.computeIfAbsent(toUserId, k -> ConcurrentHashMap.newKeySet()).add(fromUserId);
            stores.connections.computeIfAbsent(fromUserId, k -> ConcurrentHashMap.newKeySet()).add(toUserId);
            notifications.publish(fromUserId, NotificationType.CONNECTION_ACCEPTED, toUserId + " accepted your request");
        }
    }

    public Set<String> listConnections(String userId) {
        return Collections.unmodifiableSet(stores.connections.getOrDefault(userId, Set.of()));
    }
}
