package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;;
import javafx.scene.control.*;
import quizdate.model.UserDatabase;
import quizdate.model.User;

public class CreateAccountController {

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
    @FXML
    private TextField txt_password;


    public void createAccountButtonPressed(ActionEvent event) {

        User user = new User(txt_lastName.getText(), txt_firstName.getText(), dte_dateOfBirth.getValue(),
                (String) cmb_sex.getValue(), txt_email.getText(), txt_phoneNumber.getText(), txt_adres.getText(), txt_password.getText());
        System.out.println(user.getPassword());

        if (UserDatabase.getInstance().save(user)) {
            MainController.getMainController().switchSceneLogin(event, btn_createAccount);
        }

        //sqlConnection.close();
    }

    public void backButtonPressed(ActionEvent event) {
        MainController.getMainController().switchSceneLogin(event, btn_back);
    }


}
