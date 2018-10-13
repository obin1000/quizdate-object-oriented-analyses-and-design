package quizdate.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import quizdate.model.ChatRoom;
import quizdate.model.User;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ChatWindowController implements Initializable {
    @FXML
    private Button btn_settings, btn_home, btn_send, btn_chat;
    @FXML
    private Label lbl_chat;
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
        lbl_chat.setText("Chatting with: " + MAIN_CONTROLLER.getMatchedUser().getFirstName());
    }

    public void sendButtonPressed(ActionEvent event) {
        // TODO : madeQuiz baseren of degene de quiz al heeft gemaakt, en zodoende toegang geven tot de chat
        if(!madeQuiz){
            quizAlert(event,btn_send);
        }else{
            System.out.println("sending message... "+ message.getText());
            room.sendMessage(message.getText(),MAIN_CONTROLLER.getCurrentUser());
            MAIN_CONTROLLER.switchSceneChatWindow(event, btn_send,room);
        }
    }

    public void chatButtonPressed(ActionEvent event){
        MAIN_CONTROLLER.switchSceneChat(event, btn_chat);
    }

    private void quizAlert(ActionEvent event, Button buttonPressed){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Make the quiz!");
        alert.setHeaderText("You have not made the quiz which is required to chat yet.");
        alert.setContentText("Would you like to make the quiz now?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                MAIN_CONTROLLER.switchSceneMakeQuiz(event, buttonPressed);
            }else {
                MAIN_CONTROLLER.switchSceneChat(event, buttonPressed);
                System.out.println("User decided to not make the quiz.");
            }
        }else{
            System.err.println("No result was found");
        }
    }


}
