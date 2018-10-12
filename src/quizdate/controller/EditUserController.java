package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import quizdate.model.User;
import quizdate.model.UserRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class EditUserController implements Initializable {


    @FXML
    private Label name;
    @FXML
    private TextField lastName;
    @FXML
    private TextField firstName;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_deleteAccount;

    private static final MainController MAIN_CONTROLLER = MainController.getMainController();
    private static final UserRepository USER_REPOSITORY = UserRepository.getInstance();
    private User currentUser = MAIN_CONTROLLER.getCurrentUser();

    public void saveChangesButtonPressed(ActionEvent event) {
        currentUser.setLastName(lastName.getText());
        System.out.println(currentUser.getLastName());
    }

    public void backButtonPressed(ActionEvent event) {
        MAIN_CONTROLLER.switchSceneFindMatch(event, btn_back);
    }

    public void deleteAccountButtonPressed(ActionEvent event){
        USER_REPOSITORY.remove(currentUser.getUserId());
        System.out.println("User has deleted account.. | QuizDate wishes you all the best in seeking further love.");
        MAIN_CONTROLLER.switchSceneLogin(event, btn_deleteAccount);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lastName.setText(currentUser.getLastName());
    }
}
