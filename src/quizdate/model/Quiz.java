package quizdate.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private List<String> questions;
    private List<Answer> answers;
    private int score;

    public Quiz() {

        questions = new ArrayList<>();
        answers = new ArrayList<>();
        score = 0;
    }

    public void addQuestion(String question) {
        this.questions.add(question);
    }

    public void addAnswer(Answer answer) { this.answers.add(answer); }

    public List<String> getQuestions() {
        return questions;
    }

    public List<Answer> getAnswers() {
        return answers;
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
