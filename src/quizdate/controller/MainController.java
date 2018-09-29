package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import quizdate.model.SQL;

import java.io.IOException;

public class MainController {

    private int userId;
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

    public void setUserId(int userId) {
        System.out.println("UserId =" + userId + " Set via MainController.");
        this.userId = userId;
    }

    public int getUserId() {
        System.out.println("UsedId =" + userId + " Get via MainController.");
        SQL.getSingleton().getUser();

        return userId;
    }


    public static MainController getSingleton() {

        if (MainController.singleton == null) {
            MainController.singleton = new MainController();
        }
        return MainController.singleton;
    }

}