package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import quizdate.model.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditUserController implements Initializable{


    @FXML private Label name;
    @FXML private TextField lastName;
    @FXML private TextField firstName;
    @FXML private Button btn_back;

    private static LocalDate inputDa = LocalDate.of(2001,5,23);
    private static User u3 = new User("Bootje", "Mootje", inputDa, "Male", "robin", "1337","Hoorn3","password");

    public void saveChangesButtonPressed(ActionEvent event) throws IOException {
        u3.setLastName(lastName.getText());
        System.out.println(u3.getLastName());
    }

    public void backButtonPressed(ActionEvent event) throws IOException{
        getMainController().switchSceneFindMatch(event, btn_back,getMainController().getUserId());
    }

    private MainController getMainController() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/main.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        return fxmlLoader.<MainController>getController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lastName.setText(u3.getLastName());
    }

}
