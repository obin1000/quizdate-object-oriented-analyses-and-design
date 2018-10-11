package quizdate.model;

import java.util.List;

public interface ChatRoom {
    public void sendMessage(String message, User user);
    public void addUser(User user);
    public void removeUser(User user);
    public List<User> getUsers();
}