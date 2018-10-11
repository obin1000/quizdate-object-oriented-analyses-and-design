package quizdate.model;

import java.util.ArrayList;
import java.util.List;

public class ChatroomImpl implements ChatRoom {
    private List<User> users;
    private List<String> messages;

    public ChatroomImpl(){
        this.users = new ArrayList<User>();
    }
    @Override
    public void sendMessage(String message, User user) {
        messages.add(user.getFirstName()+": "+message);
        System.out.println(user + " send: " + message);
        for(User u : users){
            if(u != user){
                u.receiveMessage(user,message);
            }
        }
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }
    public void removeUser(User user) {
        this.users.remove(user);
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    public List<String> getMessages() {
        return messages;
    }
}
