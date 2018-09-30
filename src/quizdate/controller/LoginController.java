package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import quizdate.model.SQL;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_register;;

    public void loginButtonPressed(ActionEvent event) {
        int userId;
        System.out.println("Checking loginButtonPressed details...");
        if ((userId = SQL.getSingleton().checkLoginInformation(txt_username.getText(), txt_password.getText())) != 0) {
            //CHECK IF LOGIN DATA IS CORRECT, THEN DENY OR LOGIN.
            System.out.println("logindetails correct!");
            getMainController().setCurrentUser(SQL.getSingleton().getUser(userId)); //Sets the user to singleton static MainController if a userId is found.
            getMainController().switchSceneFindMatch(event, btn_login);

        } else {
            System.err.print("please enter correct information");
        }
    }

    /**
     * @param event
     * @throws IOException
     */

    public void registerButtonPressed(ActionEvent event) {
        getMainController().switchSceneCreateAccount(event, btn_register);
    }

    private MainController getMainController() {
        return MainController.getSingleton();
    }

}


