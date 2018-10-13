package quizdate.controller;
//TODO: Generate random answer possibilities, show score in the end.
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import quizdate.model.AnswerRepository;
import quizdate.model.MatchService;
import quizdate.model.UserRepository;
import quizdate.model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class MakeQuizController implements Initializable {
    @FXML
    private ImageView profilePicture;
    @FXML
    private Button btn_chat, btn_next;
    @FXML
    private RadioButton btn_answerA, btn_answerB, btn_answerC, btn_answerD;
    @FXML
    private Label lbl_username, lbl_question;

    private Image img = new Image("file:./src/quizdate/images/trump.jpg");
    private static final MainController MAIN_CONTROLLER = MainController.getMainController();
    private static final UserRepository USER_REPOSITORY = UserRepository.getInstance();
    private static final AnswerRepository ANSWER_REPOSITORY = AnswerRepository.getInstance();
    private static final MatchService MATCH_SERVICE = MatchService.getInstance();
    private User currentUser = MAIN_CONTROLLER.getCurrentUser();
    private User otherUser = MAIN_CONTROLLER.getMatchedUser();

    private int counter = 1;
    private int score = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (otherUser.getProfilePicture() == null){
            profilePicture.setImage(img);
        }else {
            profilePicture.setImage(otherUser.getProfilePicture());
        }

        lbl_username.setText(otherUser.getFirstName() + " " + otherUser.getLastName());

        setAnswers();

        // TODO : set answers and question to present the correct values.

    }

    public void nextButtonPressed(ActionEvent event){

        if(counter < 2){
            if (btn_answerA.isSelected()){
                if (btn_answerA.getText().equals(ANSWER_REPOSITORY.get(counter).getRightAnswer())) {
                    score++;
                }
                counter++;
            }
            else if (btn_answerB.isSelected()){
                if (btn_answerB.getText().equals(ANSWER_REPOSITORY.get(counter).getRightAnswer())) {
                    score++;
                }
                counter++;
            }
            else if (btn_answerC.isSelected()){
                if (btn_answerC.getText().equals(ANSWER_REPOSITORY.get(counter).getRightAnswer())) {
                    score++;
                }
                counter++;
            }
            else if (btn_answerD.isSelected()){
                if (btn_answerD.getText().equals(ANSWER_REPOSITORY.get(counter).getRightAnswer())) {
                    score++;
                }
                counter++;
            }
            else {
                System.out.println("NOTHING HAS BEEN SELECTED");
            }
    } else {
            //TO TEST.
            MAIN_CONTROLLER.switchSceneLogin(event, btn_next);
        }
    }

    public void chatButtonPressed(ActionEvent event){
        MAIN_CONTROLLER.switchSceneChat(event,btn_chat);
        //TODO : Zorgen dat de usergegevens via ChatController achterhaald kunnen worden.
        //TODO : Wanneer bovenstaand compleet is, kan onderstaan geuncomment worden
//        User reset = null;
//        MAIN_CONTROLLER.setMatchedUser(reset);
    }

    private void setAnswers() {
        btn_answerA.setText("New answer!");
        btn_answerB.setText(ANSWER_REPOSITORY.get(counter).getRightAnswer());
        btn_answerC.setText("Answer D");
        btn_answerD.setText("Answer D");
        lbl_question.setText(ANSWER_REPOSITORY.getQuestion(counter));
    }
}

