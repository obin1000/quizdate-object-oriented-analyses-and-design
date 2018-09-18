package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;

public class create_account_controller extends MainController{

    @FXML
    private TextField txt_lastName;
    @FXML
    private TextField txt_firstName;
    @FXML
    private DatePicker dte_dateOfBirth;
    @FXML
    private ComboBox cmb_sex;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_phoneNumber;
    @FXML
    private TextField txt_adres;
    @FXML
    private Button btn_createAccount;
    @FXML
    private Button btn_back;

    public void createAccountButtonPressed (ActionEvent event) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(txt_lastName.getText() + "\n");
        sb.append(txt_firstName.getText() + "\n");
        sb.append(dte_dateOfBirth.getValue() + "\n");
        sb.append(txt_email.getText() + "\n");
        sb.append(txt_phoneNumber.getText() + "\n");
        sb.append(txt_adres.getText());
        System.out.println(sb.toString());
        super.switchSceneLogin(event,btn_createAccount);
    }

    public void backButtonPressed (ActionEvent event) throws IOException {
        super.switchSceneLogin(event, btn_back);
    }

}
