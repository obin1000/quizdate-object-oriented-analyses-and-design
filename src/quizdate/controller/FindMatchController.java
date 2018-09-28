package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FindMatchController extends MainController implements Initializable {
    @FXML private ImageView profilePicture;
    @FXML private Button btn_settings;

    private Image img = new Image("file:./src/quizdate/images/trump.jpg");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profilePicture.setImage(img);
        System.out.println("test123");
    }

    public void dislikeButtonPressed(ActionEvent event) throws IOException {
        System.out.println("Dislike button clicked...");
    }

    public void likeButtonPressed(ActionEvent event) throws IOException {
        System.out.println("Like button clicked...");
    }

    public void settingsButtonPressed(ActionEvent event) throws IOException {
        System.out.println("settings button clicked...");
        super.switchSceneEditUser(event,btn_settings);
    }

    public void chatButtonPressed(ActionEvent event) throws IOException {
        System.out.println("Chat button clicked...");
    }
}

