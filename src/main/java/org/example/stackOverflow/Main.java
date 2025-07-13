package org.example.stackOverflow;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        StackOverflowPlatform platform = new StackOverflowPlatform();
        User alice = new User("Alice");
        User bob = new User("Bob");

        Tag java = new Tag("Java");
        Tag design = new Tag("Design");

        Question q = platform.postQuestion("What is LLD?", "Can someone explain LLD with examples?", alice, List.of(java, design));

        platform.voteQuestion(q, bob, VotingType.UPVOTE);
        platform.postAnswer(q, "LLD means breaking down a system into components.", bob);
        platform.voteAnswer(q.getAnswers().get(0), alice, VotingType.UPVOTE);

        platform.printQuestionDetails(q);
    }
}
