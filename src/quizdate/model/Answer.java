package quizdate.model;

public class Answer {

    private int questionId;
    private String answer;
    private String givenAnswer;

    public Answer(int questionId, String answer, String givenAnswer) {

        this.questionId = questionId;
        this.answer = answer;
        this.givenAnswer = givenAnswer;

    }

    public Answer(int questionId, String answer) {
        this.questionId = questionId;
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

    public int getQuestionId() { return questionId; }

}
