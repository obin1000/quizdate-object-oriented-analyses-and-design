package quizdate.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import quizdate.SQL;
import quizdate.User;

import java.io.IOException;

public class CreateAccountController extends MainController {

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
        
        User user = new User(txt_lastName.getText(), txt_firstName.getText(), dte_dateOfBirth.getValue(),
            (String) cmb_sex.getValue(), txt_email.getText(), txt_phoneNumber.getText(), txt_adres.getText());
            
        SQL sqlConnection = new SQL();

        if (sqlConnection.saveUser(user)){
                     super.switchSceneLogin(event,btn_createAccount);
        }

        sqlConnection.close();
    }

    public void backButtonPressed (ActionEvent event) throws IOException {
        super.switchSceneLogin(event, btn_back);
    }

}
