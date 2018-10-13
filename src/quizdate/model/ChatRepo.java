package quizdate.model;

import java.util.List;

public interface ChatRepo {
    public ChatRoom getById(int id);
    public List<ChatRoom> getAll();
    public void add(ChatRoom chat);
    public void remove(ChatRoom chat);
    public void edit(ChatRoom chat);
}
