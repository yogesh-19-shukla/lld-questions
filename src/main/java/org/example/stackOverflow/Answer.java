package org.example.stackOverflow;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Answer {
    private final String id;
    private final String body;
    private final User author;
    private final List<Vote> votes = new ArrayList<>();

    public Answer(String body, User author) {
        this.id = UUID.randomUUID().toString();
        this.body = body;
        this.author = author;
    }

    public void vote(User user, VotingType type) {
        votes.removeIf(v -> v.getUserId().equals(user.getUserId())); // only one vote per user
        votes.add(new Vote(user.getUserId(), type));
    }

    public int getVoteCount() {
        return (int) votes.stream().filter(v -> v.getVoteType() == VotingType.UPVOTE).count()
                - (int) votes.stream().filter(v -> v.getVoteType() == VotingType.DOWNVOTE).count();
    }

    public String getBody() { return body; }
    public String getAuthor() { return author.getName(); }
}
