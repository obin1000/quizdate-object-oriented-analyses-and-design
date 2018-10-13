package quizdate.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import quizdate.model.ChatRoom;

import java.net.URL;
import java.util.Optional;
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
    private boolean madeQuiz = false;

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
        view.scrollTo(view.getItems().size());
    }

    public void sendButtonPressed(ActionEvent event) {
        System.out.println("sending message... "+ message.getText());
        room.sendMessage(message.getText(),MAIN_CONTROLLER.getCurrentUser());
        if(!madeQuiz){
            quizAlert(event,btn_send);
        }else{
            MAIN_CONTROLLER.switchSceneChatWindow(event, btn_send,room);
        }
    }

    public void settingsButtonPressed(ActionEvent event) {
        System.out.println("settings button clicked...");
        MAIN_CONTROLLER.switchSceneEditUser(event, btn_settings);
    }

    public void homeButtonPressed(ActionEvent event) {
        System.out.println("Chat button clicked...");
        MAIN_CONTROLLER.switchSceneFindMatch(event, btn_home);
    }

    private void quizAlert(ActionEvent event, Button buttonPressed){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Make the quiz!");
        alert.setHeaderText("You have not made the quiz which is required to chat yet.");
        alert.setContentText("Would you like to make the quiz now?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK){
            MAIN_CONTROLLER.switchSceneMakeQuiz(event,btn_send);
        }else{
//            MAIN_CONTROLLER.switchSceneChat(event,btn_home);
            System.out.println("NOPEEEE :)");
        }
    }


}
