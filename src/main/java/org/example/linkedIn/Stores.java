package org.example.linkedIn;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Stores {

    final Map<String, User> users = new ConcurrentHashMap<>();
    final Map<String, Profile> profiles = new ConcurrentHashMap<>();
    final Map<String, Set<String>> connections = new ConcurrentHashMap<>(); // userId -> set of accepted connections
    final Map<String, Set<String>> pendingOutgoing = new ConcurrentHashMap<>();
    final Map<String, Set<String>> pendingIncoming = new ConcurrentHashMap<>();

    final Map<String, List<Message>> inbox = new ConcurrentHashMap<>();
    final Map<String, List<Message>> outbox = new ConcurrentHashMap<>();

    final Map<String, Job> jobs = new ConcurrentHashMap<>();
    final Map<String, Set<String>> jobApplicants = new ConcurrentHashMap<>();
}
