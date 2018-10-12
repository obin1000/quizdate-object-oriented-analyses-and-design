package quizdate.model;

import javafx.scene.image.Image;
import java.time.LocalDate;
import java.util.*;

public class User extends Observable implements Chatter {
    private static int latestUserId = 1000;
    private int userId;
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private String sex;
    private String email;
    private String phoneNumber;
    private String adres;
    private Image profilePicture;
    private Date creationDate;
    private List<User> Likes;
    private List<User> Matches;
    private String password;
    private List<Observer> observers = new ArrayList<>();
    private List<ChatRoom> chats;

    public User(String lastName, String firstName, LocalDate dateOfBirth, String sex, String email,
                String phoneNumber, String adres, String password) {
        this.userId = latestUserId++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.adres = adres;
        this.creationDate = new Date();
        this.Likes = new ArrayList<>();
        this.Matches = new ArrayList<>();
        this.password = password;
        this.chats = new ArrayList<>();
    }

    /**
     * Getters & Setters
     */

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyObservers();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyObservers();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        notifyObservers();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        notifyObservers();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyObservers();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyObservers();
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres() {
        this.adres = adres;
        notifyObservers();
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
        notifyObservers();
    }

    public List<User> getLikes(){
        return Likes;
    }

    public List<User> getMatches(){
        return Matches;
    }

    public String getPassword() {
        return password;
    }

    /**
     * METHODS
     */

    public String toString(){
        return userId + " " + firstName + " " + lastName;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers(){
        for(Observer observer : observers){
            observer.update(this,null);
        }
    }

    @Override
    public void receiveMessage(Chatter roomMate, String message) {
        System.out.println(this + " received: " + message);
    }
    public List<ChatRoom> getChats(){
        return chats;
    }
    public ChatRoom getChat(int index){
        if(chats.get(index) != null){
            return chats.get(index);
        }
        else{
            return null;
        }
    }


}