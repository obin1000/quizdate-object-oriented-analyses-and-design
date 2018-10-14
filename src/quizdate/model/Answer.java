package quizdate.model;

public class Answer {

    private int questionId;
    private String rightAnswer;
    private String givenAnswer;

    public Answer(int questionId, String rightAnswer, String givenAnswer) {

        this.questionId = questionId;
        this.rightAnswer = rightAnswer;
        this.givenAnswer = givenAnswer;

    }

    public Answer(int questionId, String rightAnswer) {
        this.questionId = questionId;
        this.rightAnswer = rightAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    public int getQuestionId() { return questionId; }

}
