package quizdate.model;

import java.util.List;

public class Question {
    private String question;
    private List<Character> answers;
    private char correctAnswer;

    public Question(){

    }
    public String getQuestion(){
        return question;
    }
    public void setQuestion(String q){
        this.question = q;
    }
    public boolean isCorrect(){
        return true;
    }
    public String toString(){
        return question;
    }
}
