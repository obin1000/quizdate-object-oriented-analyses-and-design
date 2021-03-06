package quizdate.model;

import javafx.scene.image.Image;
import java.time.LocalDate;
import java.util.*;

public class User extends Observable implements Chatter {
    private int userId;
    private Quiz quiz;
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

    public User(String lastName, String firstName, LocalDate dateOfBirth, String sex, String email,
                String phoneNumber, String adres, String password) {
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
    }

    /**
     * Getters & Setters
     */

    public Quiz getQuiz() { return quiz;}
    public void setQuiz(Quiz quiz) {this.quiz=quiz;}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<User> getLikes() {
        return Likes;
    }

    public void setLikes(List<User> likes) {
        Likes = likes;
    }

    public List<User> getMatches() {
        return Matches;
    }

    public void setMatches(List<User> matches) {
        Matches = matches;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * METHODS
     */

    public String toString(){
        return firstName + " " + lastName;
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


}