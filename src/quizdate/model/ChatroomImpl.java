package quizdate.model;

import java.util.ArrayList;
import java.util.List;

public class ChatroomImpl implements ChatRoom {
    private List<Chatter> users;
    private List<String> messages;

    public ChatroomImpl(){
        this.users = new ArrayList<Chatter>();
        this.messages = new ArrayList<>();
    }
    public ChatroomImpl(List<Chatter> users, String messages){
        this.users = users;
        this.messages = new ArrayList<>();
        this.messages.add(messages);
    }
    @Override
    public void sendMessage(String message, Chatter user) {
        System.out.println(user + " send: " + message);
        messages.add(user+": "+message);
        for(Chatter u : users){
            if(u != user){
                u.receiveMessage(user,message);
            }
        }
    }

    @Override
    public void addUser(Chatter user) {
        this.users.add(user);
    }
    public void removeUser(Chatter user) {
        this.users.remove(user);
    }

    @Override
    public List<Chatter> getUsers() {
        return users;
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }

    @Override
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
    public void setMessages(String messages) {
        this.messages.add(messages);
    }

    @Override
    public String toString() {
        return users.toString() + messages.toString();
    }
}
