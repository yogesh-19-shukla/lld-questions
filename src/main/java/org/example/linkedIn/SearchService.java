package org.example.linkedIn;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SearchService {

    private final Stores stores;
    // token -> userIds / jobIds
    private final Map<String, Set<String>> userIndex = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> jobIndex  = new ConcurrentHashMap<>();

    SearchService(Stores stores) { this.stores = stores; }

    // Build or update indices (call on profile updates/job posts)
    public void indexUser(String userId) {
        User u = stores.users.get(userId);
        Profile p = stores.profiles.get(userId);
        if (u == null || p == null) return;
        tokenize(u.email + " " + nvl(p.headline) + " " + nvl(p.summary) + " " + String.join(" ", p.skills))
                .forEach(tok -> userIndex.computeIfAbsent(tok, k -> ConcurrentHashMap.newKeySet()).add(userId));
    }

    public void indexJob(String jobId) {
        Job j = stores.jobs.get(jobId);
        if (j == null) return;
        tokenize(j.title + " " + j.location + " " + j.description + " " + j.requirements)
                .forEach(tok -> jobIndex.computeIfAbsent(tok, k -> ConcurrentHashMap.newKeySet()).add(jobId));
    }

    public List<String> searchUsers(String query) {
        Set<String> ids = lookup(userIndex, query);
        // very naive ranking: intersection size with tokens (already filtered)
        return new ArrayList<>(ids);
    }

    public List<String> searchJobs(String query) {
        Set<String> ids = lookup(jobIndex, query);
        return new ArrayList<>(ids);
    }

    private Set<String> lookup(Map<String, Set<String>> index, String q) {
        List<String> toks = tokenize(q);
        if (toks.isEmpty()) return Set.of();
        Set<String> acc = new HashSet<>(index.getOrDefault(toks.get(0), Set.of()));
        for (int i = 1; i < toks.size(); i++) {
            acc.retainAll(index.getOrDefault(toks.get(i), Set.of()));
        }
        return acc;
    }

    private static List<String> tokenize(String s) {
        return Arrays.stream(s.toLowerCase().split("[^a-z0-9]+"))
                .filter(t -> !t.isBlank()).collect(Collectors.toList());
    }
    private static String nvl(String s) { return s == null ? "" : s; }
}
