package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.time.LocalDate;

public class create_account_controller {

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
        StringBuilder sb = new StringBuilder();
        sb.append(txt_lastName.getText() + "\n");
        sb.append(txt_firstName.getText() + "\n");
        sb.append(dte_dateOfBirth.getValue() + "\n");
        sb.append(txt_email.getText() + "\n");
        sb.append(txt_phoneNumber.getText() + "\n");
        sb.append(txt_adres.getText());
        System.out.println(sb.toString());
//        final DatePicker datePicker = new DatePicker();
//        datePicker.setOnAction(new EventHandler() {
//            public void handle(Event t) {
//                LocalDate date = datePicker.getValue();
//                System.err.println("Selected date: " + date);
//            }
//        });
    }
}
