package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private Button btn_register;
    SQL sql = new SQL();
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

    public void loginButtonPressed(ActionEvent event) throws IOException {

        if (sql.checkLoginInformation(txt_username.getText(),txt_password.getText()) && textEntered()) {
            //CHECK IF LOGIN DATA IS CORRECT, THEN DENY OR LOGIN.
            System.out.println("Checking loginButtonPressed details...");
            System.out.println("logindetails correct!");
            System.out.println(txt_username.getText());
            System.out.println(txt_password.getText());
            getMainController().setUserId(5);
            getMainController().switchSceneFindMatch(event,btn_login,userId);

        } else {
            System.err.print("please enter correct information");
        }

    }

    /**
     * @param event
     * @throws IOException
     */

    public void registerButtonClicked(ActionEvent event) throws IOException{
        getMainController().switchSceneCreateAccount(event,btn_register);
    }

    private MainController getMainController() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/main.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        return fxmlLoader.<MainController>getController();
    }
}


