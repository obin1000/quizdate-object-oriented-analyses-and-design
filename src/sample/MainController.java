package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private void switchScene (ActionEvent event, Button btn_register, String sceneName) throws IOException {
        Stage appStage;
        Parent newRoot;
        if (event.getSource() == btn_register) {
            appStage = (Stage) btn_register.getScene().getWindow();
            newRoot = FXMLLoader.load(getClass().getResource(sceneName));
            Scene scene = new Scene(newRoot);
            appStage.setScene(scene);
            appStage.show();
        }
    }

    public void switchSceneCreateAccount(ActionEvent event, Button pressedButton) throws IOException {
        switchScene(event,pressedButton, "create_account.fxml");
    }

    public void switchSceneLogin(ActionEvent event, Button pressedButton) throws IOException {
        switchScene(event,pressedButton, "login.fxml");
    }
}