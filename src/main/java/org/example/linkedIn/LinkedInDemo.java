package org.example.linkedIn;

public class LinkedInDemo {

    public static void main(String[] args) throws InterruptedException {
        Stores stores = new Stores();
        NotificationService notifications = new NotificationService();

        // Subscribe two users to notifications console
        notifications.subscribe("any", n -> System.out.println("[N] " + n.userId + " <- " + n.type + " : " + n.payload));

        AuthService auth = new AuthService(stores);
        ProfileService profiles = new ProfileService(stores);
        ConnectionService connections = new ConnectionService(stores, notifications);
        MessagingService messaging = new MessagingService(stores, notifications);
        JobService jobs = new JobService(stores, notifications);
        SearchService search = new SearchService(stores);

        // Register + login
        String u1 = auth.register("alice@ex.com", "alice123");
        String u2 = auth.register("bob@ex.com", "bob123");
        String t1 = auth.login("alice@ex.com", "alice123");
        String t2 = auth.login("bob@ex.com", "bob123");
        System.out.println("Tokens: " + t1 + " | " + t2);

        // Profiles
        profiles.updateHeadline(u1, "SDE-2 @ Amazon");
        profiles.addSkill(u1, "Java"); profiles.addSkill(u1, "Distributed Systems");
        profiles.updateHeadline(u2, "Hiring Manager @ Startup");
        profiles.addSkill(u2, "Leadership");
        search.indexUser(u1); search.indexUser(u2);

        // Connections
        connections.sendRequest(u1, u2);
        connections.respond(u2, u1, true);
        System.out.println("Alice connections: " + connections.listConnections(u1));

        // Messaging
        messaging.send(u1, u2, "Hi Bob, nice to connect!");
        System.out.println("Bob inbox: " + messaging.inbox(u2).size());

        // Jobs
        String j1 = jobs.postJob(u2, "Backend Engineer", "Build high-scale APIs", "Java, Kafka, AWS", "Bangalore");
        search.indexJob(j1);
        jobs.apply(u1, j1);

        // Search
        System.out.println("Search users 'java': " + search.searchUsers("java"));
        System.out.println("Search jobs 'backend bangalore': " + search.searchJobs("backend bangalore"));

        // Let notifications flush
        Thread.sleep(300);
        notifications.shutdown();
    }
}
