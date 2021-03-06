package quizdate.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import quizdate.model.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    @FXML
    private Button btn_settings,btn_home;
    @FXML
    private ListView view;

    private static final MainController MAIN_CONTROLLER = MainController.getMainController();
    private static final MatchService MATCH_SERVICE = MatchService.getInstance();
    private static final UserRepository USER_REPOSITORY = UserRepository.getInstance();
    private User currentUser = MAIN_CONTROLLER.getCurrentUser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("getting matches");
        List<ChatRoom> chats = ChatRepoSQL.getInstance().getUserChatrooms(currentUser);
        view.getItems().removeAll();

        for(ChatRoom chat: chats){
            StringBuilder name = new StringBuilder();
            for(Chatter user : chat.getUsers()){
                if(user.getUserId() != currentUser.getUserId()) {
                    name.append(user);
                    System.out.println("\n" + name.toString());
                }
            }
            Button button = new Button(name.toString());
            button.setPrefSize(500, 100);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for(Chatter user : chat.getUsers()){
                        if(user != currentUser) {
                            MAIN_CONTROLLER.setMatchedUser((User)user);
                        }
                    }
                    MAIN_CONTROLLER.switchSceneChatWindow(event, button,chat);

                }
            });
            view.getItems().add(button);
        }

    }

    public void homeButtonPressed(ActionEvent event) {
        System.out.println("Home button clicked...");
        MAIN_CONTROLLER.switchSceneFindMatch(event, btn_home);
    }


}
