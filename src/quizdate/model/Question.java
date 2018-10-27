package quizdate.model;

public class Question {

    private int questionId;
    private String question;
    private String answer;
    private String givenAnswer;

    public Question(int questionId, String question, String answer, String givenAnswer) {

        this.question = question;
        this.answer = answer;
        this.givenAnswer = givenAnswer;

    }

    public Question() {

    }


    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String rightAnswer) {
        this.answer = rightAnswer;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    public String getQuestion() { return question; }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }


}
