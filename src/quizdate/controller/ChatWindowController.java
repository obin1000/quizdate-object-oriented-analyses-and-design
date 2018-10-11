package quizdate.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    @FXML
    private Button btn_send;
    @FXML
    private ListView view;
    @FXML
    private TextField message;

    private ChatRoom room;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Loading chat");
        view.getItems().removeAll();
        view.setPlaceholder(new Label("No messages? Time to make the first move!"));
        room = MainController.getRequestedRoom();
        for(String s:room.getMessages()){
            view.getItems().add(s);
        }

    }

    public void sendButtonPressed(ActionEvent event) {
        System.out.println("sending message... "+ message.getText());
        room.sendMessage(message.getText(),MainController.getMainController().getCurrentUser());
        MainController.getMainController().switchSceneChatWindow(event, btn_send,room);
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
