package quizdate.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class find_match_controller implements Initializable {
    @FXML
    private ImageView profilePicture;
    Image img = new Image("file:./src/quizdate/images/like.png");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profilePicture.setImage(img);
        System.out.println("test123");
    }








}
