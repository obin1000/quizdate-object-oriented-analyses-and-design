package quizdate.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import quizdate.model.ChatRoom;

import java.net.URL;
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

    private static final MainController MAIN_CONTROLLER = MainController.getMainController();
    private ChatRoom room;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Loading chat");
        view.getItems().removeAll();
        view.setPlaceholder(new Label("No messages? Time to make the first move!"));
        room = MAIN_CONTROLLER.getRequestedRoom();
        for (String s : room.getMessages()) {
            view.getItems().add(s);
        }
    }

    public void sendButtonPressed(ActionEvent event) {
        System.out.println("sending message... "+ message.getText());
        room.sendMessage(message.getText(),MAIN_CONTROLLER.getCurrentUser());
        MAIN_CONTROLLER.switchSceneChatWindow(event, btn_send,room);
    }

    public void settingsButtonPressed(ActionEvent event) {
        System.out.println("settings button clicked...");
        MAIN_CONTROLLER.switchSceneEditUser(event, btn_settings);
    }

    public void homeButtonPressed(ActionEvent event) {
        System.out.println("Chat button clicked...");
        MAIN_CONTROLLER.switchSceneFindMatch(event, btn_home);
    }

}
