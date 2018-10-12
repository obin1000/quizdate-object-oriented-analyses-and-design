package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import quizdate.model.MatchService;
import quizdate.model.UserRepository;
import quizdate.model.User;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FindMatchController implements Initializable {
    @FXML
    private ImageView profilePicture;
    @FXML
    private Button btn_settings;
    @FXML
    private Button btn_chat;
    @FXML
    private Button btn_like;
    @FXML
    private Button btn_dislike;
    @FXML
    private Label lbl_username;

    private Image img = new Image("file:./src/quizdate/images/trump.jpg");
    private User otherUser;
    private final static MainController mainController = MainController.getMainController();
    private User currentUser = mainController.getCurrentUser();
    private final static MatchService MATCH_SERVICE = MatchService.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        otherUser = UserRepository.getInstance().get(UserRepository.getInstance().getRandomId());
        if (otherUser.getProfilePicture() == null){ profilePicture.setImage(img);}
        else {profilePicture.setImage(otherUser.getProfilePicture());}
        lbl_username.setText(otherUser.getFirstName() + " " + otherUser.getLastName());
    }

    public void dislikeButtonPressed(ActionEvent event) {
        MATCH_SERVICE.denyMatch(otherUser);
        System.out.println("User " + currentUser.getFirstName() + " Disliked " + otherUser.getFirstName());

//        MainController.getMainController().getCurrentUser().denyMatch(otherUser); //call dislike function in currentuser
//        System.out.println("USER " + MainController.getMainController().getCurrentUser().getFirstName() + " DISLIKED "
//                + otherUser.getFirstName());
        mainController.switchSceneFindMatch(event, btn_like); // refresh screen to load new user
    }

    public void likeButtonPressed(ActionEvent event) {
        MATCH_SERVICE.acceptMatch(currentUser,otherUser);
        System.out.println(currentUser.getFirstName() + " LIKED " + otherUser.getFirstName());

//        MainController.getMainController().getCurrentUser().acceptMatch(otherUser); // call the like function in user
//        System.out.println("USER " + MainController.getMainController().getCurrentUser().getFirstName() + " LIKED "
//            + otherUser.getFirstName());
        mainController.switchSceneFindMatch(event, btn_dislike); // refresh the otherUser
    }

    public void settingsButtonPressed(ActionEvent event) {
        System.out.println("settings button clicked...");
        mainController.switchSceneEditUser(event, btn_settings);
    }

    public void chatButtonPressed(ActionEvent event) {
        System.out.println("Chat button clicked...");
        mainController.switchSceneChat(event, btn_chat);
    }


}

