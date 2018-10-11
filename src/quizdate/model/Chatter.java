package quizdate.model;

public interface Chatter {
    public abstract void receiveMessage(Chatter roomMate, String message);
}
