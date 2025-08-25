package org.example.linkedIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class JobService {

    private final Stores stores;
    private final NotificationService notifications;
    private final AtomicLong ids = new AtomicLong(0L);

    JobService(Stores stores, NotificationService notifications) { this.stores = stores; this.notifications = notifications; }

    public String postJob(String employerId, String title, String desc, String req, String location) {
        String id = "j-" + ids.incrementAndGet();
        Job job = new Job(id, employerId, title, desc, req, location);
        stores.jobs.put(id, job);
        // fanout (demo): notify all connections of employer
        for (String conn : stores.connections.getOrDefault(employerId, Set.of())) {
            notifications.publish(conn, NotificationType.JOB_POSTED, "New job: " + title + " @ " + location);
        }
        return id;
    }

    public void apply(String userId, String jobId) {
        stores.jobApplicants.computeIfAbsent(jobId, k -> ConcurrentHashMap.newKeySet()).add(userId);
    }

    public List<Job> listAll() { return new ArrayList<>(stores.jobs.values()); }
}
