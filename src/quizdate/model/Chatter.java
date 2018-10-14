package quizdate.model;

public interface Chatter {
    public int getUserId();
    public void receiveMessage(Chatter roomMate, String message);
}
