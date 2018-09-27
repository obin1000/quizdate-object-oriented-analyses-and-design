package quizdate.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import quizdate.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditUserController extends MainController implements Initializable {


    @FXML Label name;
    @FXML TextField lastName;
    @FXML TextField firstName;
    @FXML Button btn_back;


    LocalDate inputDa = LocalDate.of(2001,5,23);
    User u3 = new User("Bootje", "Mootje", inputDa, "Male", "robin", "1337","Hoorn3");

    public void saveChangesButtonPressed(ActionEvent event) throws IOException {
        u3.setLastName(lastName.getText());
        System.out.println(u3.getLastName());
    }

    public void backButtonPressed(ActionEvent event) throws IOException{
        super.switchSceneFindMatch(event,btn_back);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lastName.setText(u3.getFirstName());
    }
}
