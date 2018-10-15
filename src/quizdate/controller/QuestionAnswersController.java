package quizdate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import quizdate.model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QuestionAnswersController implements Initializable {

    private final static AnswerRepository ANSWER_REPOSITORY = AnswerRepository.getInstance();
    private final static MainController MAIN_CONTROLLER = MainController.getMainController();
    private final static UserRepository USER_REPOSITORY = UserRepository.getInstance();

    private Quiz quiz;

    @FXML
    private Label text_question1, text_question2, text_question3, text_question4, text_question5;

    @FXML
    private Button btn_back, btn_createAccount;

    @FXML
    private TextField text_answer1, text_answer2, text_answer3, text_answer4, text_answer5;

    @FXML
    private Pane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quiz = new Quiz();
        for (int i = 1; i < 6; i++) {
            quiz.addQuestion(ANSWER_REPOSITORY.getQuestion(i));
        }

        text_question1.setText(quiz.getQuestions().get(0));
        text_question2.setText(quiz.getQuestions().get(1));
        text_question3.setText(quiz.getQuestions().get(2));
        text_question4.setText(quiz.getQuestions().get(3));
        text_question5.setText(quiz.getQuestions().get(4));

        text_question1.layoutXProperty().bind(pane.widthProperty().subtract(text_question1.widthProperty()).divide(2));
        text_question2.layoutXProperty().bind(pane.widthProperty().subtract(text_question2.widthProperty()).divide(2));
        text_question3.layoutXProperty().bind(pane.widthProperty().subtract(text_question3.widthProperty()).divide(2));
        text_question4.layoutXProperty().bind(pane.widthProperty().subtract(text_question4.widthProperty()).divide(2));
        text_question5.layoutXProperty().bind(pane.widthProperty().subtract(text_question5.widthProperty()).divide(2));

    }

    public void createAccountButtonPressed(ActionEvent event) {

        USER_REPOSITORY.save(MAIN_CONTROLLER.getRegisteredUser());
        MAIN_CONTROLLER.setCurrentUser(MAIN_CONTROLLER.getRegisteredUser(),
                USER_REPOSITORY.getUserId(MAIN_CONTROLLER.getRegisteredUser()));

        quiz.addAnswer(new Answer(1, text_answer1.getText()));
        quiz.addAnswer(new Answer(2, text_answer2.getText()));
        quiz.addAnswer(new Answer(3, text_answer3.getText()));
        quiz.addAnswer(new Answer(4, text_answer4.getText()));
        quiz.addAnswer(new Answer(5, text_answer5.getText()));

        for (int i = 0; i < 5; i++) {
            ANSWER_REPOSITORY.save(quiz.getAnswers().get(i));
        }

        MAIN_CONTROLLER.removeCurrentUser();
        MAIN_CONTROLLER.removeRegisteredUser();

        MAIN_CONTROLLER.switchSceneLogin(event, btn_createAccount);
    }
}
