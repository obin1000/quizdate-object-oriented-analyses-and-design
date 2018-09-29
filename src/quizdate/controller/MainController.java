package quizdate.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private int userId;

    private void switchSceneWithUserId (ActionEvent event, Button pressedButton, String sceneName, int userId) throws IOException {
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
    private void switchSceneWithoutUserId (ActionEvent event, Button pressedButton, String sceneName) throws IOException {
        System.out.println("Calls switchSceneWithoutUserId");
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
        System.out.println("Calls switchSceneCreateAccount ");
        switchSceneWithoutUserId(event,pressedButton, "../view/create_account.fxml");
    }

    public void switchSceneLogin(ActionEvent event, Button pressedButton) throws IOException {
        switchSceneWithoutUserId(event,pressedButton, "../view/login.fxml");
    }

    public void switchSceneFindMatch(ActionEvent event, Button pressedButton, int userId) throws IOException{
        switchSceneWithUserId(event,pressedButton,"../view/find_match.fxml",userId);
    }

    public void switchSceneEditUser(ActionEvent event, Button pressedButton) throws IOException{
        switchSceneWithoutUserId(event,pressedButton,"../view/edit_user.fxml");
    }

    public void setUserId(int userId){
        System.out.println("UserId = Set via MainController.");
        this.userId = userId;
    }

    public int getUserId(){
        System.out.println("UsedId = Get via MainController.");
        return userId;
    }

    @FXML
    private void initialize() {

        Platform.runLater(() -> {



        });

    }
}