package org.example.linkedIn;

public class Job {

    final String id;
    final String employerId;
    final String title;
    final String description;
    final String requirements;
    final String location;

    Job(String id, String employerId, String title, String description, String requirements, String location) {
        this.id = id;
        this.employerId = employerId;
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.location = location;
    }
}
