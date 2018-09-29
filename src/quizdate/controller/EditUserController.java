package quizdate.controller;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import quizdate.model.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditUserController extends MainController implements Initializable{


    @FXML private Label name;
    @FXML private TextField lastName;
    @FXML private TextField firstName;
    @FXML private Button btn_back;
    private ObservableList <User> userList = FXCollections.observableArrayList();

    private static LocalDate inputDa = LocalDate.of(2001,5,23);
    private static User u3 = new User("Bootje", "Mootje", inputDa, "Male", "robin", "1337","Hoorn3");

    public void saveChangesButtonPressed(ActionEvent event) throws IOException {
        u3.setLastName(lastName.getText());
        userList.add(u3);
        System.out.println(u3.getLastName());
    }

    public void backButtonPressed(ActionEvent event) throws IOException{
        super.switchSceneFindMatch(event,btn_back);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lastName.setText(u3.getLastName());
        userList.add(u3);
        userList.addListener(new InvalidationListener() {
            @Override
            public void invalidated(javafx.beans.Observable observable) {

            }
        });
    }

}
