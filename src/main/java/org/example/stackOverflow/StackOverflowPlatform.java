package org.example.stackOverflow;

import java.util.*;

public class StackOverflowPlatform {
    private final List<Question> questions = new ArrayList<>();

    public Question postQuestion(String title, String desc, User user, List<Tag> tags) {
        Question question = new Question(title, desc, user);
        tags.forEach(question::addTag);
        questions.add(question);
        return question;
    }

    public void postAnswer(Question question, String body, User user) {
        question.addAnswer(new Answer(body, user));
    }

    public void voteQuestion(Question question, User user, VotingType type) {
        question.vote(user, type);
    }

    public void voteAnswer(Answer answer, User user, VotingType type) {
        answer.vote(user, type);
    }

    public void printQuestionDetails(Question question) {
        System.out.println("Question: " + question.getTitle() + " - by " + question.getAuthor());
        System.out.println("Description: " + question.getDescription());
        System.out.println("Votes: " + question.getVoteCount());
        System.out.print("Tags: ");
        question.getTags().forEach(t -> System.out.print(t.getName() + " "));
        System.out.println("\n--- Answers ---");
        for (Answer a : question.getAnswers()) {
            System.out.println("- " + a.getBody() + " [by " + a.getAuthor() + ", Votes: " + a.getVoteCount() + "]");
        }
        System.out.println("---------------");
    }
}
