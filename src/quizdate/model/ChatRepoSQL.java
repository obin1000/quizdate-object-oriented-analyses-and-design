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
            PreparedStatement statement;
            statement = dbConnection.getInstance().prepare("UPDATE Chatroom SET Messages = ? WHERE Roomid = ?");
            statement.setString(1, temp);
            statement.setInt(2, room.getRoomid());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
    }

    @Override
    public List<ChatRoom> getUserChatrooms(int Userid) {
        List<ChatRoom> rooms = new ArrayList<>();
        try {
            PreparedStatement statement;
            statement = dbConnection.getInstance().prepare("SELECT * FROM UserChatroom uc " +
                    "WHERE uc.userId = ?");
            statement.setInt(1, Userid);
            ResultSet rs = statement.executeQuery();

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
            PreparedStatement statement;
            statement = dbConnection.getInstance().prepare("SELECT * FROM Chatroom u " +
                    "INNER JOIN UserChatroom uc on uc.Roomid = u.Roomid " +
                    "WHERE u.Roomid = ? " +
                    "ORDER BY u.Roomid asc;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

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
            PreparedStatement statement;
            ResultSet back;
            do {
                roomnr++;
                statement = dbConnection.getInstance().prepare("SELECT Roomid FROM Chatroom WHERE Roomid = ?");
                statement.setInt(1, roomnr);
                back = statement.executeQuery();
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
            PreparedStatement statement;
            statement = dbConnection.getInstance().prepare("SELECT Roomid FROM Chatroom WHERE Roomid = ?");
            statement.setInt(1,chat.getRoomid());
            ResultSet back = statement.executeQuery();
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
                PreparedStatement statement;
                statement = dbConnection.getInstance().prepare("INSERT INTO Chatroom(Roomid, Messages) " +
                        "VALUES (?,?)");
                statement.setInt(1, roomid);
                statement.setString(2, temp);

                statement.executeUpdate();

                for (Chatter c : chat.getUsers()) {
                    statement = dbConnection.getInstance().prepare("INSERT INTO UserChatroom(userId,Roomid) " +
                            "VALUES (?, ?)");
                    statement.setInt(1, c.getUserId());
                    statement.setInt(2, roomid);
                    statement.executeUpdate();
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
            PreparedStatement statement;
            statement = dbConnection.getInstance().prepare("DELETE FROM Chatroom WHERE userId = ?");
            statement.setInt(1, chat.getRoomid());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
    }

}
