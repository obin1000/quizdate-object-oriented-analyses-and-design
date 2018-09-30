package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import quizdate.model.SQL;
import quizdate.model.User;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MainController implements Observer {

    private User user;
    private static MainController singleton;


    public MainController() {
        MainController.singleton = this;
    }

    private void switchScene(ActionEvent event, Button pressedButton, String sceneName) {
        Stage appStage;
        Parent newRoot;
        if (event.getSource() == pressedButton) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.addObserver(this);
    }

    public static MainController getSingleton() {

        if (MainController.singleton == null) {
            MainController.singleton = new MainController();
        }
        return MainController.singleton;
    }

    @Override
    public void update(Observable observable, Object o) {
        if(SQL.getSingleton().saveUser(user)) {
            System.out.println("MainController has just updated the user in the databasee.... :D");
        }
    }
}