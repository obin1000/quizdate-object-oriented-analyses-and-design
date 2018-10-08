package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import quizdate.model.UserDatabase;

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

    public void saveChangesButtonPressed(ActionEvent event) {
        MainController.getMainController().getCurrentUser().setLastName(lastName.getText());
        System.out.println(MainController.getMainController().getCurrentUser().getLastName());
    }

    public void backButtonPressed(ActionEvent event) {
        MainController.getMainController().switchSceneFindMatch(event, btn_back);
    }

    public void deleteAccountButtonPressed(ActionEvent event){
        UserDatabase.getInstance().remove(MainController.getMainController().getCurrentUser().getUserId());
        System.out.println("User has deleted account.. | QuizDate wishes you all the best in seeking further love.");
        MainController.getMainController().switchSceneLogin(event, btn_deleteAccount);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lastName.setText(MainController.getMainController().getCurrentUser().getLastName());
    }



}
