package quizdate.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private List<String> questions;
    private List<Answer> answers;

    public Quiz() {

        questions = new ArrayList<>();
        answers = new ArrayList<>();
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



}
