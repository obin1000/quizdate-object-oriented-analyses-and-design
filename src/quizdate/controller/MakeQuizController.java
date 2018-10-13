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
import java.util.ResourceBundle;

public class MakeQuizController implements Initializable {
    @FXML
    private ImageView profilePicture;
    @FXML
    private Button btn_chat, btn_answerA, btn_answerB, btn_answerC, btn_answerD;
    @FXML
    private Label lbl_username, lbl_question;

    private Image img = new Image("file:./src/quizdate/images/trump.jpg");
    private static final MainController MAIN_CONTROLLER = MainController.getMainController();
    private static final UserRepository USER_REPOSITORY = UserRepository.getInstance();
    private static final MatchService MATCH_SERVICE = MatchService.getInstance();
    private User currentUser = MAIN_CONTROLLER.getCurrentUser();
    private User otherUser = MAIN_CONTROLLER.getMatchedUser();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (otherUser.getProfilePicture() == null){
            profilePicture.setImage(img);
        }else {
            profilePicture.setImage(otherUser.getProfilePicture());
        }

        lbl_username.setText(otherUser.getFirstName() + " " + otherUser.getLastName());

        // TODO : set answers and question to present the correct values.
        btn_answerA.setText("Answer A");
        btn_answerB.setText("Answer B");
        btn_answerC.setText("Answer C");
        btn_answerD.setText("Answer D");
        lbl_question.setText("What is the correct answer?");
    }

    public void chatButtonPressed(ActionEvent event){
        MAIN_CONTROLLER.switchSceneChat(event,btn_chat);
        //TODO : Zorgen dat de usergegevens via ChatController achterhaald kunnen worden.
        //TODO : Wanneer bovenstaand compleet is, kan onderstaan geuncomment worden
//        User reset = null;
//        MAIN_CONTROLLER.setMatchedUser(reset);
    }

}

