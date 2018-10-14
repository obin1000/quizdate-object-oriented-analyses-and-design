package quizdate.model;

import java.util.List;

public interface ChatRoom {
    public void sendMessage(String message, Chatter user);
    public void addUser(Chatter user);
    public void removeUser(Chatter user);
    public List<Chatter> getUsers();
    public List<String> getMessages();
    public void setMessages(List<String> messages);
}