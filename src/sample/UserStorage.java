package sample;

import java.io.*;

public class UserStorage {
    public void saveUser(User u){
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(u.getUserID));
        output.writeObject(u);
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public User getUser(int UserID) throws ClassNotFoundException {
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(String.format("%s",UserID)));
            return (User) input.readObject();
    } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    }
