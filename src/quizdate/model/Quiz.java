package quizdate.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private List<Question> questions;
    private int score;

    public Quiz() {

        questions = new ArrayList<>();
        score = 0;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
        System.out.println("added question = " + question.getQuestion());
    }


    public Question getQuestion(int id) {
        return questions.get(id);
    }
    public List<Question> getQuestions() {
        return questions;
    }


    public int getScore() {
        return score;
    }

    public void addScore() {
        score++;
    }

    public void removeScore() {
        score--;
    }
}
