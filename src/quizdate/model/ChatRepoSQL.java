package quizdate.model;

import java.util.ArrayList;
import java.util.List;

public class ChatRepoSQL implements ChatRepo {
    private final static ChatRepoSQL singleton;
    private List<ChatRoom> rooms;

    static {
        singleton = new ChatRepoSQL();
    }

    private ChatRepoSQL() {
        rooms = new ArrayList<>();
    }

    public static ChatRepoSQL getInstance() {
        return ChatRepoSQL.singleton;
    }

    @Override
    public ChatRoom getById(int id) {
        return rooms.get(id);
    }

    @Override
    public List<ChatRoom> getAll() {
        return rooms;
    }

    @Override
    public void add(ChatRoom chat) {
        rooms.add(chat);
    }

    @Override
    public void remove(ChatRoom chat) {
        rooms.remove(chat);

    }

    @Override
    public void edit(ChatRoom chat) {
        //TODO To be used
    }
}
