package quizdate.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private List<String> questions;

    public Quiz() {

        questions = new ArrayList<>();

    }

    public void addQuestion(String question) {
        this.questions.add(question);
    }



}
