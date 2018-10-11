package quizdate.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import quizdate.model.ChatRoom;
import quizdate.model.User;
import quizdate.model.UserDatabase;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatWindowController implements Initializable {
    @FXML
    private Button btn_settings;
    @FXML
    private Button btn_home;

    public void initialize(URL location, ResourceBundle resources,ChatRoom chat) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
