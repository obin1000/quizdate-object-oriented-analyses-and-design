package quizdate.model;




import javafx.beans.InvalidationListener;

import java.awt.Image;
import java.time.LocalDate;
import java.util.*;

public class User extends Observable {
//    private static int latestUserId = 1000;
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
    private List<Integer> Likes;
    private List<Integer> Matches;
    private String password;
    private List<Observer> observers = new ArrayList<>();

    public User(String lastName, String firstName, LocalDate dateOfBirth, String sex, String email,
                String phoneNumber, String adres, String password) {
//        this.userId = latestUserId++;
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

    /*
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

    public List<Integer> getLikes(){
        return Likes;
    }

    public List<Integer> getMatches(){
        return Matches;
    }

    public String getPassword() {
        return password;
    }

    /*
     * Methods
     */

    public boolean hasMatch(User user) {
        return Matches.contains(user.getUserId());
    }

    public boolean hasLike(User user) {
        return Likes.contains(user.getUserId());
    }

    public void addToLiked(int likedPerson){
        this.Likes.add(likedPerson);
        notifyObservers();
    }

    public void addToMatches(int matchId){
        this.Matches.add(matchId);
        notifyObservers();
    }

    public void addToMatches(User user){
        this.Matches.add(user.getUserId());
        notifyObservers();
    }

    public void removeMatch(int uid){
        Matches.remove((Integer) uid);
        notifyObservers();
    }

    public void removeMatch(User user){
        Matches.remove((Integer) user.getUserId());
        notifyObservers();
    }

    public void removeLike(int uid){
        Likes.remove((Integer) uid);
        notifyObservers();
    }

    public void removeLike(User user){
        Likes.remove((Integer) user.getUserId());
        notifyObservers();
    }

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
}