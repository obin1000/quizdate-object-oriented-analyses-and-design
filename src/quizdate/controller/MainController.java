package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private void switchScene (ActionEvent event, Button pressedButton, String sceneName) throws IOException {
        Stage appStage;
        Parent newRoot;
        if (event.getSource() == pressedButton) {
            appStage = (Stage) pressedButton.getScene().getWindow();
            newRoot = FXMLLoader.load(getClass().getResource(sceneName));
            Scene scene = new Scene(newRoot);
            appStage.setScene(scene);
            appStage.show();
        }
    }

    public void switchSceneCreateAccount(ActionEvent event, Button pressedButton) throws IOException {
        switchScene(event,pressedButton, "../view/create_account.fxml");
    }

    public void switchSceneLogin(ActionEvent event, Button pressedButton) throws IOException {
        switchScene(event,pressedButton, "../view/login.fxml");
    }

    public void switchSceneFindMatch(ActionEvent event, Button pressedButton) throws IOException{
        switchScene(event,pressedButton,"../view/find_match.fxml");
    }

    public void switchSceneEditUser(ActionEvent event, Button pressedButton) throws IOException{
        switchScene(event,pressedButton,"../view/edit_user.fxml");
    }
}