package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class login_controller extends MainController {

    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_register;

    public boolean loginInformationCorrect() {
        boolean loginInformation = false;
        if (txt_username.getText().equals("email"))
            if (txt_password.getText().equals("password")) {
                loginInformation = true;
            }
        return loginInformation;
    }

    public boolean textEntered() {
        return true;
    }

    public void login(ActionEvent event) {

        if (loginInformationCorrect() && textEntered()) {
            //CHECK IF LOGIN DATA IS CORRECT, THEN DENY OR LOGIN.
            System.out.println("Checking login details...");
            System.out.println("logindetails correct!");
            System.out.println(txt_username.getText());
            System.out.println(txt_password.getText());
        } else {
            System.err.print("please enter correct information");
        }

    }

    /**
     * @param event
     * @throws IOException
     */
    public void registerButtonClicked(ActionEvent event) throws IOException {
        super.switchSceneCreateAccount(event, btn_register);
    }


    }


