package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowResultController implements Initializable {

    @FXML
    private Pane pane;

    @FXML
    private Button btn_next;

    @FXML
    private Label text_rightAnswer, text_givenAnswer, text_correctCounter, text_question;

    private int counter = 0;
    private static final MainController MAIN_CONTROLLER = MainController.getMainController();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text_givenAnswer.layoutXProperty().bind(pane.widthProperty().subtract(text_givenAnswer.widthProperty()).divide(2));
        text_rightAnswer.layoutXProperty().bind(pane.widthProperty().subtract(text_rightAnswer.widthProperty()).divide(2));
        text_question.layoutXProperty().bind(pane.widthProperty().subtract(text_question.widthProperty()).divide(2));
        setResults();
    }

    public void nextButtonPressed(ActionEvent event){
        if (counter > 4) {
            MAIN_CONTROLLER.switchSceneChat(event, btn_next);
        } else {
            setResults();
        }
    }


    private void setResults() {

        text_correctCounter.setText(Integer.toString(MAIN_CONTROLLER.getQuizData().getScore()));
        text_rightAnswer.setText(MAIN_CONTROLLER.getQuizData().getAnswers().get(counter).getRightAnswer());
        text_givenAnswer.setText(MAIN_CONTROLLER.getQuizData().getAnswers().get(counter).getGivenAnswer());
        text_question.setText(MAIN_CONTROLLER.getQuizData().getQuestions().get(counter));


        text_rightAnswer.setTextFill(Color.GREEN);

        if (MAIN_CONTROLLER.getQuizData().getAnswers().get(counter).getRightAnswer().equals(MAIN_CONTROLLER.
                getQuizData().getAnswers().get(counter).getGivenAnswer())) {
            text_givenAnswer.setTextFill(Color.GREEN);
        } else {
            text_givenAnswer.setTextFill(Color.RED);
        }
        counter++;
    }

}
