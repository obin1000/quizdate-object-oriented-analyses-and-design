package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.time.LocalDate;

public class create_account_controller {

    private User user;
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

    public void createAccount(ActionEvent e) {

        user = new User(txt_lastName.getText(), txt_firstName.getText(), dte_dateOfBirth.getValue(),
                (String) cmb_sex.getValue(), txt_email.getText(), txt_phoneNumber.getText(), txt_adres.getText());
    }
}
