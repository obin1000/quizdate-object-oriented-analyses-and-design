package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import quizdate.model.SQL;
import quizdate.model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class FindMatchController implements Initializable {
    @FXML
    private ImageView profilePicture;
    @FXML
    private Button btn_settings;
    @FXML
    private Button btn_like;
    @FXML
    private Button btn_dislike;
    @FXML
    private Label lbl_username;

    private Image img = new Image("file:./src/quizdate/images/trump.jpg");
    private User match;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        match = SQL.getDatabase().getUser(SQL.getDatabase().getRandomId());
        if (match.getProfilePicture() == null){ profilePicture.setImage(img);}
        else {profilePicture.setImage(match.getProfilePicture());}
        lbl_username.setText(match.getFirstName() + " " + match.getLastName());
    }

    public void dislikeButtonPressed(ActionEvent event) {
        MainController.getMainController().getCurrentUser().denyMatch(match); //call dislike function in currentuser
        System.out.println("USER " + MainController.getMainController().getCurrentUser().getFirstName() + " DISLIKED "
                + match.getFirstName());
        MainController.getMainController().switchSceneFindMatch(event, btn_like); // refresh the match
    }

    public void likeButtonPressed(ActionEvent event) {
        MainController.getMainController().getCurrentUser().acceptMatch(match); // call the like function in user
        System.out.println(MainController.getMainController().getCurrentUser().getPassword());
        System.out.println("USER " + MainController.getMainController().getCurrentUser().getFirstName() + " LIKED "
            + match.getFirstName());
        MainController.getMainController().switchSceneFindMatch(event, btn_dislike); // refresh the match
    }

    public void settingsButtonPressed(ActionEvent event) {
        System.out.println("settings button clicked...");
        MainController.getMainController().switchSceneEditUser(event, btn_settings);
    }

    public void chatButtonPressed(ActionEvent event) {
        System.out.println("Chat button clicked...");
    }

}

