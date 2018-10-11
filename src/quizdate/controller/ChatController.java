package quizdate.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import quizdate.model.ChatRoom;
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
    private ListView view;

    private static int debug = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(debug==1) {
            System.out.println("debug");
            MainController.getMainController().getCurrentUser().createMatch(UserDatabase.getInstance().get(11));
            MainController.getMainController().getCurrentUser().createMatch(UserDatabase.getInstance().get(12));
            MainController.getMainController().getCurrentUser().createMatch(UserDatabase.getInstance().get(13));
            MainController.getMainController().getCurrentUser().createMatch(UserDatabase.getInstance().get(14));
            MainController.getMainController().getCurrentUser().createMatch(UserDatabase.getInstance().get(15));
            MainController.getMainController().getCurrentUser().createMatch(UserDatabase.getInstance().get(16));
            debug++;
        }
        System.out.println("getting matches");
        List<ChatRoom> chats = MainController.getMainController().getCurrentUser().getChats();
        view.getItems().removeAll();
        for(ChatRoom c: chats){
            StringBuilder name = new StringBuilder();
            for(User u : c.getUsers()){
                if(u !=MainController.getMainController().getCurrentUser()) {
                    name.append(u);
                }
            }
            Button button = new Button(name.toString());
            button.setPrefSize(500, 100);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    MainController.getMainController().switchSceneChatWindow(event, button);
                }
            });
            view.getItems().add(button);
        }

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
