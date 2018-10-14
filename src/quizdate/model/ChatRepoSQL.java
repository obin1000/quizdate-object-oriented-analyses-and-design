package quizdate.model;

import javafx.scene.image.Image;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChatRepoSQL implements ChatRepo {
    private final static ChatRepoSQL singleton;

    static {
        singleton = new ChatRepoSQL();
    }

    private ChatRepoSQL() {

    }

    public static ChatRepoSQL getInstance() {
        return ChatRepoSQL.singleton;
    }

    @Override
    public List<ChatRoom> getUserChatrooms(int Userid) {
        List<ChatRoom> rooms = new ArrayList<>();
        try {
            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM UserChatroom uc\n" +
                    "WHERE uc.userId = " + Userid);

            while (rs.next()) {
                rooms.add(getById(rs.getInt("Roomid")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public List<ChatRoom> getUserChatrooms(User User) {
        return getUserChatrooms(User.getUserId());
    }

    @Override
    public ChatRoom getById(int id) {
        ChatroomImpl chat = new ChatroomImpl();
        int i = 1;
        try {
            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Chatroom u\n" +
                    "INNER JOIN UserChatroom uc on uc.Roomid = u.Roomid\n" +
                    "WHERE u.Roomid = "+id+"\n" +
                    "ORDER BY u.Roomid asc;");

            while (rs.next()) {
                if(i ==1) {
                    chat.setMessages(rs.getString("Messages"));
                    i++;
                }
                chat.addUser(UserRepository.getInstance().get(rs.getInt("UserId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chat;
    }
    public int getRoomid(){
        int roomnr=0;
        try {
            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            ResultSet back;
            do {
                roomnr++;
                back = statement.executeQuery("SELECT Roomid FROM Chatroom WHERE Roomid =" + roomnr);
            }while(back.next());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return roomnr;
    }
    @Override
    public List<ChatRoom> getAll() {
        //TODO: too be implemented when needed
        List<ChatRoom> chat = new ArrayList<>();
        return chat;
    }

    @Override
    public void add(ChatRoom chat) {
        int roomid = getRoomid();
        StringBuilder string = new StringBuilder();
        for(String s: chat.getMessages()){
            string.append(s+"\n");
        }
        String temp = string.toString();
        try {
            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            statement.executeUpdate("INSERT INTO Chatroom(Roomid, Messages) VALUES ('"+ roomid +"','"+ temp + "')");
            for(Chatter c : chat.getUsers()) {
                statement.executeUpdate("INSERT INTO UserChatroom(userId,Roomid) VALUES ('" + c.getUserId() +"','"+ roomid + "')");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
    }

    @Override
    public void remove(ChatRoom chat) {
    }

    @Override
    public void edit(ChatRoom chat) {
        //TODO To be used
    }
}
