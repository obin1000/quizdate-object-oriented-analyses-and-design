package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import quizdate.model.ChatRoom;
import quizdate.model.UserRepository;
import quizdate.model.User;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public final class MainController implements Observer {

    private User currentUser;
    private static final MainController singleton;
    private static ChatRoom requestedroom;
    private static final UserRepository USER_REPOSITORY = UserRepository.getInstance();

    static{
        singleton = new MainController();
    }


    private MainController() {
    }

    private void switchScene(ActionEvent event, Button pressedButton, String sceneName) {
        Stage appStage;
        Parent newRoot;
            appStage = (Stage) pressedButton.getScene().getWindow();
            try {
                newRoot = FXMLLoader.load(getClass().getResource(sceneName));
                Scene scene = new Scene(newRoot);
                appStage.setScene(scene);
                appStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    public void switchSceneCreateAccount(ActionEvent event, Button pressedButton) {
        System.out.println("Calls switchSceneCreateAccount ");
        switchScene(event, pressedButton, "../view/create_account.fxml");
    }

    public void switchSceneLogin(ActionEvent event, Button pressedButton) {
        switchScene(event, pressedButton, "../view/login.fxml");
    }

    public void switchSceneFindMatch(ActionEvent event, Button pressedButton) {
        switchScene(event, pressedButton, "../view/find_match.fxml");
    }

    public void switchSceneEditUser(ActionEvent event, Button pressedButton) {
        switchScene(event, pressedButton, "../view/edit_user.fxml");
    }
    public void switchSceneChat(ActionEvent event, Button pressedButton) {
        switchScene(event, pressedButton, "../view/chat.fxml");
    }
    public void switchSceneChatWindow(ActionEvent event, Button pressedButton, ChatRoom chat) {
        requestedroom = chat;
        switchScene(event, pressedButton, "../view/chatwindow.fxml");
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser, int userId) {
        this.currentUser = currentUser;
        currentUser.setUserId(userId);
        currentUser.addObserver(this);
    }

    public static MainController getMainController() {
        return singleton;
    }

    public static ChatRoom getRequestedRoom() {
        return requestedroom;
    }

    @Override
    public void update(Observable observable, Object o) {
        System.out.println(currentUser.getUserId());
        if(USER_REPOSITORY.update(currentUser.getUserId(), currentUser)) {
            System.out.println("MainController has just user updated " + currentUser + " :)");
        }
    }
}