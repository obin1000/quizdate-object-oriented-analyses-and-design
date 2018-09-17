package sample;

import java.awt.*;
import java.time.LocalDate;
import java.util.Date;

public class User {
    private static int userId = 1000;
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private String sex;
    private String email;
    private String phoneNumber;
    private String adres;
    private Image profilePicture;
    private Date creationDate;

    public User(String lastName, String firstName, LocalDate dateOfBirth, String sex, String email,
                String phoneNumber, String adres) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.adres = adres;
        this.creationDate = new Date();
    }

    //Methods


    //Getters & Setters

    public int getUserId() {
        return userId;
        
    }
    
    public void setUserId(int userId) {
        User.userId = userId
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

    public String isSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }
}