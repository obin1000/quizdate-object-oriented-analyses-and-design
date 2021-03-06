package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import quizdate.model.MatchService;
import quizdate.model.UserRepository;
import quizdate.model.User;

import java.net.URL;
import java.util.Optional;
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
    private static final MainController MAIN_CONTROLLER = MainController.getMainController();
    private static final UserRepository USER_REPOSITORY = UserRepository.getInstance();
    private static final MatchService MATCH_SERVICE = MatchService.getInstance();
    private User currentUser = MAIN_CONTROLLER.getCurrentUser();
    private User otherUser;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        otherUser = USER_REPOSITORY.get(USER_REPOSITORY.getRandomId());
        if (otherUser.getProfilePicture() == null){
            profilePicture.setImage(img);
        }else {
            profilePicture.setImage(otherUser.getProfilePicture());
        }
        //TODO - remove statement below before launch, this is to simulate a match.
        MATCH_SERVICE.addToLiked(otherUser,currentUser);
        lbl_username.setText(otherUser.getFirstName() + " " + otherUser.getLastName());
    }

    public void dislikeButtonPressed(ActionEvent event) {
        MATCH_SERVICE.denyMatch(otherUser);
        System.out.println("User " + currentUser.getFirstName() + " Disliked " + otherUser.getFirstName());
        MAIN_CONTROLLER.switchSceneFindMatch(event, btn_like); // refresh screen to load new user
    }

    public void likeButtonPressed(ActionEvent event) {
        // Adds otherUser to currentUser's like list, and checks if they are a match already.
        if(MATCH_SERVICE.acceptMatch(currentUser,otherUser)){
            System.out.println(currentUser.getFirstName() + " LIKED " + otherUser.getFirstName());
            matchAlert(event);
        }else{
            MAIN_CONTROLLER.switchSceneFindMatch(event, btn_dislike); // refresh the otherUser
        }
    }

    public void settingsButtonPressed(ActionEvent event) {
        System.out.println("settings button clicked...");
        MAIN_CONTROLLER.switchSceneEditUser(event, btn_settings);
    }

    public void chatButtonPressed(ActionEvent event) {
        System.out.println("Chat button clicked...");
        MAIN_CONTROLLER.switchSceneChat(event, btn_chat);
    }

    private void matchAlert(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("You have a match!");
        alert.setHeaderText("You have a match with " + otherUser.getFirstName() + " " + otherUser.getLastName());
        alert.setContentText("Would you like to make the quiz? :D");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            MAIN_CONTROLLER.setMatchedUser(otherUser);
            MAIN_CONTROLLER.switchSceneMakeQuiz(event,btn_like);
            System.out.println("User would love to chat with other user!!");
        }else{
            MAIN_CONTROLLER.switchSceneFindMatch(event,btn_like);
            System.out.println("User has decided to keep on liking.");
        }
    }


}

