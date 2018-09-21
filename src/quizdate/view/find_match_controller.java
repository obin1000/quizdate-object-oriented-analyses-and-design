package quizdate.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class find_match_controller implements Initializable {
    @FXML private ImageView profilePicture;


    Image img = new Image("file:./src/quizdate/images/like.png");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profilePicture.setImage(img);
        System.out.println("test123");
    }
    public void dislikeButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Dislike button clicked...");
    }

    public void likeButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Like button clicked...");
    }

    public void settingsButtonClicked(ActionEvent event) throws IOException {
        System.out.println("settings button clicked...");
    }

    public void chatButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Chat button clicked...");
    }
}

