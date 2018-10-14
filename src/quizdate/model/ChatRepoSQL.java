package quizdate.model;

import javafx.scene.image.Image;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void update(ChatRoom room) {
        StringBuilder string = new StringBuilder();
        for (String s : room.getMessages()) {
            string.append(s + "\n");
        }
        String temp = string.toString();
        try {
            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            statement.executeUpdate("UPDATE Chatroom SET Messages ='"+temp+"'  WHERE Roomid = " +  room.getRoomid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
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
        List<String> messages = new ArrayList<>();
        String message;
        int i = 1;
        try {
            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Chatroom u\n" +
                    "INNER JOIN UserChatroom uc on uc.Roomid = u.Roomid\n" +
                    "WHERE u.Roomid = "+id+"\n" +
                    "ORDER BY u.Roomid asc;");

            while (rs.next()) {
                if(i ==1) {
                    message = rs.getString("Messages");
                    messages.addAll(Arrays.asList(message.split("\\r?\\n")));
                    chat.setMessages(messages);
                    chat.setRoomid(rs.getInt("Roomid"));
                    i++;
                }
                chat.addUser(UserRepository.getInstance().get(rs.getInt("UserId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chat;
    }
    public int getRoomNewId(){
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
    public boolean roomInDatabase(ChatRoom chat){
        try {
            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            ResultSet back = statement.executeQuery("SELECT Roomid FROM Chatroom WHERE Roomid =" + chat.getRoomid());
            if(back.next()){
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return true;
    }

    //add new room to the database
    @Override
    public void add(ChatRoom chat) {
        if (roomInDatabase(chat)){
            System.err.println("Room already in database, use update instead");
        }else {
            int roomid = getRoomNewId();
            StringBuilder string = new StringBuilder();
            for (String s : chat.getMessages()) {
                string.append(s + "\n");
            }
            String temp = string.toString();
            try {
                Statement statement = dbConnection.getInstance().getConnection().createStatement();
                statement.executeUpdate("INSERT INTO Chatroom(Roomid, Messages) VALUES ('" + roomid + "','" + temp + "')");
                for (Chatter c : chat.getUsers()) {
                    statement.executeUpdate("INSERT INTO UserChatroom(userId,Roomid) VALUES ('" + c.getUserId() + "','" + roomid + "')");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbConnection.getInstance().close();
        }
    }

    @Override
    public void remove(ChatRoom chat) {

        try {
            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            statement.executeUpdate("DELETE FROM Chatroom WHERE userId = " + chat.getRoomid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
    }

}
