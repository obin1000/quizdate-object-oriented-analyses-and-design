package quizdate.model;

import java.util.List;

public interface ChatRepo {
    public  void update(ChatRoom room);
    public List<ChatRoom> getUserChatrooms(int Userid);
    public List<ChatRoom> getUserChatrooms(User User);
    public ChatRoom getById(int id);
    public List<ChatRoom> getAll();
    public void add(ChatRoom chat);
    public void remove(ChatRoom chat);
}
