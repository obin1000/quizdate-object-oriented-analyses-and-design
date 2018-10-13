package quizdate.model;

public class Answer {

    private int questionId;
    private String rightAnswer;
    private String[] possibleAnswers;

    public Answer(int questionId, String rightAnswer) {

        this.questionId = questionId;
        this.rightAnswer = rightAnswer;
        //this.possibleAnswers = possibleAnswers;

    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(String[] possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public int getQuestionId() { return questionId; }

}
