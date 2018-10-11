package quizdate.model;

import quizdate.model.Question;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends Question {

    private List<Question> children;

    public Quiz(String question, String rightAnswer, String[] possibleAnswers) {

        super(question, rightAnswer, possibleAnswers);
        children = new ArrayList<>();

    }

    public void addQuestion(Question question) {
        this.children.add(question);
    }

}
