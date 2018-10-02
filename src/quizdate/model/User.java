package quizdate.model;

import javafx.scene.image.Image;
import java.time.LocalDate;
import java.util.*;

public class User extends Observable {
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

    public List<User> getLikes(){
        return Likes;
    }

    public List<User> getMatches(){
        return Matches;
    }

    public String getPassword() {
        return password;
    }

    /*
     * Methods
     */

    public boolean hasMatch(User user) {
        return Matches.contains(user);
    }

    public boolean hasLike(User user) {
        return Likes.contains(user);
    }

    public void addToLiked(User user){
        this.Likes.add(user);
        notifyObservers();
    }

    public void addToMatches(User user){
        this.Matches.add(user);
        notifyObservers();
    }


    public void removeFromMatch(User user){
        Matches.remove(user);
        notifyObservers();
    }

    public void removeFromLike(User user){
        Likes.remove(user);
        notifyObservers();
    }

    public void checkForMatch(User u2){
        if (this.hasLike(u2)){
            if (u2.hasLike(this)){
                createMatch(u2);
            }
        }
    }

    public boolean createMatch(User u2){
        this.addToMatches(u2); //add user2 to user1s matches
        u2.addToMatches(this); // add user1 to user2s matches
        return true;
    }

    // Remove a Match between two users
    public boolean removeMatch(User u2){
        this.removeFromLike(u2);
        this.removeFromMatch(u2);
        u2.removeMatch(this);
        return true;
    }

    // Is called when the user presses the accept button
    public void acceptMatch(User u2){
        this.addToLiked(u2);  // add user2 to user1s likes list
        checkForMatch(u2);
    }
    // Is called when user presses the deny button
    public  void denyMatch(User u2){
        System.out.println("that is not very nice for " + u2.getFirstName());
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