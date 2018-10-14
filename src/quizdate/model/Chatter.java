package quizdate.model;

public interface Chatter {
    public int getUserId();
    public abstract void receiveMessage(Chatter roomMate, String message);
}
