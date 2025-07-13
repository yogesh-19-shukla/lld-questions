package org.example.stackOverflow;

import java.util.*;

public class Question {

    private final String id;
    private final String title;
    private final String description;
    private final User author;
    private final Set<Tag> tags = new HashSet<>();
    private final List<Answer> answers = new ArrayList<>();
    private final List<Vote> votes = new ArrayList<>();

    public Question(String title, String description, User author) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void vote(User user, VotingType type) {
        votes.removeIf(v -> v.getUserId().equals(user.getUserId()));
        votes.add(new Vote(user.getUserId(), type));
    }

    public int getVoteCount() {
        return (int) votes.stream().filter(v -> v.getVoteType() == VotingType.UPVOTE).count()
                - (int) votes.stream().filter(v -> v.getVoteType() == VotingType.DOWNVOTE).count();
    }

    public List<Answer> getAnswers() { return answers; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getAuthor() { return author.getName(); }
    public Set<Tag> getTags() { return tags; }

}
