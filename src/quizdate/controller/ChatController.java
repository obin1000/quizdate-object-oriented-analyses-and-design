package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import quizdate.model.User;
import quizdate.model.UserDatabase;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    @FXML
    private Button btn_settings;
    @FXML
    private Button btn_home;
    @FXML
    private ScrollPane scrollPane;

    private List<User> matches;

    private String[] test = {"BOB","HENK","HARRY","GERRIT","KLAAS","BAAS"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("getting matches");
        matches = MainController.getMainController().getCurrentUser().getMatches();
        Button a = new Button("hello");
        a.setPrefSize(400,300);
        scrollPane = new ScrollPane();
        scrollPane.setContent(a);
//        for(User u:matches){
//
//        }
    }
    public void settingsButtonPressed(ActionEvent event) {
        System.out.println("settings button clicked...");
        MainController.getMainController().switchSceneEditUser(event, btn_settings);
    }

    public void homeButtonPressed(ActionEvent event) {
        System.out.println("Chat button clicked...");
        MainController.getMainController().switchSceneFindMatch(event, btn_home);
    }


}
