package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import quizdate.model.ChatRoom;
import quizdate.model.Quiz;
import quizdate.model.UserRepository;
import quizdate.model.User;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public final class MainController implements Observer {

    private static final MainController SINGLETON;
    private static final UserRepository USER_REPOSITORY = UserRepository.getInstance();
    private ChatRoom requestedroom;
    private User matchedUser;
    private User currentUser;
    private Quiz quizData;
    private User registeredUser;

    static{
        SINGLETON = new MainController();
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

    public void switchQuestionAnswers(ActionEvent event, Button pressedButton) {
        switchScene(event, pressedButton, "../view/QuestionAnswers.fxml");
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

    public void switchSceneMakeQuiz(ActionEvent event, Button pressedButton) {
        switchScene(event, pressedButton, "../view/makeQuiz.fxml");
    }

    public void switchSceneQuizResult(ActionEvent event, Button pressedButton) {
        switchScene(event, pressedButton, "../view/ShowQuizResults.fxml");
    }



    public User getCurrentUser() {
        return currentUser;
    }

    public void removeCurrentUser() {
        currentUser = null;
    }

    public void setCurrentUser(User currentUser, int userId) {
        this.currentUser = currentUser;
        currentUser.setUserId(userId);
        currentUser.addObserver(this);
    }

    public User getMatchedUser() {
        return matchedUser;
    }

    public User getRegisteredUser() { return registeredUser; }

    public void setRegisteredUser(User registeredUser) {
        this.registeredUser = registeredUser;
    }

    public void removeRegisteredUser() {
        registeredUser = null;
    }

    public boolean setMatchedUser(User matchedUser) {
        boolean status = false;
        try {
            this.matchedUser = matchedUser;
            matchedUser.setUserId(USER_REPOSITORY.getUserId(matchedUser));
            status = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }

    public static MainController getMainController() {
        return SINGLETON;
    }

    public ChatRoom getRequestedRoom() {
        return requestedroom;
    }

    public void setRequestedroom(ChatRoom chat){
        requestedroom = chat;
    }

    @Override
    public void update(Observable observable, Object o) {
        System.out.println(currentUser.getUserId());
        if(USER_REPOSITORY.update(currentUser.getUserId(), currentUser)) {
            System.out.println("MainController has just user updated " + currentUser + " :)");
        }
    }

    public Quiz getQuizData() {
        return quizData;
    }

    public void setQuizData(Quiz quizData) {
        this.quizData = quizData;
    }
}