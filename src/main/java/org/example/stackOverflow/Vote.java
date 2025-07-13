package org.example.stackOverflow;

public class Vote {
    private final String userId;
    private final VotingType voteType;

    public Vote(String userId, VotingType voteType) {
        this.userId = userId;
        this.voteType = voteType;
    }

    public String getUserId() {
        return userId;
    }

    public VotingType getVoteType() {
        return voteType;
    }

}
