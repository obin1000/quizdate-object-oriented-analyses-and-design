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
    private int userId = 1;

//    public boolean loginInformationCorrect() {
//        boolean loginInformation = false;
////        if (txt_username.getText().equals("email"))
////            if (txt_password.getText().equals("password")) {
////                loginInformation = true;
////            }
//        return loginInformation;
//    }

    public boolean textEntered() {
        return true;
    }

    public void loginButtonPressed(ActionEvent event) {

        if (SQL.getSingleton().checkLoginInformation(txt_username.getText(), txt_password.getText()) != 0 &&textEntered()) {
            //CHECK IF LOGIN DATA IS CORRECT, THEN DENY OR LOGIN.
            System.out.println("Checking loginButtonPressed details...");
            System.out.println("logindetails correct!");
            getMainController().setUserId(SQL.getSingleton().checkLoginInformation(txt_username.getText(),txt_password.getText()));
            getMainController().switchSceneFindMatch(event, btn_login);

        } else {
            System.err.print("please enter correct information");
        }

    }

    /**
     * @param event
     * @throws IOException
     */

    public void registerButtonClicked(ActionEvent event) {
        getMainController().switchSceneCreateAccount(event, btn_register);
    }

    private MainController getMainController() {
        return MainController.getSingleton();
    }

}


