package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import quizdate.model.User;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditUserController implements Initializable {


    @FXML
    private Label name;
    @FXML
    private TextField lastName;
    @FXML
    private TextField firstName;
    @FXML
    private Button btn_back;

    private LocalDate inputDa = LocalDate.of(2001, 5, 23);
    private User u3 = new User("Bootje", "Mootje", inputDa, "Male", "robin", "1337", "Hoorn3", "password");

    public void saveChangesButtonPressed(ActionEvent event) {
        u3.setLastName(lastName.getText());
        System.out.println(u3.getLastName());
    }

    public void backButtonPressed(ActionEvent event) {
        getMainController().switchSceneFindMatch(event, btn_back);
    }

    private MainController getMainController() {
        return MainController.getSingleton();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lastName.setText(u3.getLastName());
    }

}
