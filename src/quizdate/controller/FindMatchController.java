package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class FindMatchController implements Initializable {
    @FXML
    private ImageView profilePicture;
    @FXML
    private Button btn_settings;
    private Image img = new Image("file:./src/quizdate/images/trump.jpg");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profilePicture.setImage(img);
        System.out.println("test123");
    }

    public void dislikeButtonPressed(ActionEvent event) {
        System.out.println("Dislike button clicked...");
    }

    public void likeButtonPressed(ActionEvent event) {
        System.out.println(MainController.getMainController().getCurrentUser().getPassword());
        System.out.println("Like button clicked...");
    }

    public void settingsButtonPressed(ActionEvent event) {
        System.out.println("settings button clicked...");
        MainController.getMainController().switchSceneEditUser(event, btn_settings);
    }

    public void chatButtonPressed(ActionEvent event) {
        System.out.println("Chat button clicked...");
    }

}

