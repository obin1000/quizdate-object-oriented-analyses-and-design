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
import java.util.ResourceBundle;

public class QuestionAnswersController implements Initializable {

    private final static QuizRepository QUIZ_REPOSITORY = QuizRepository.getInstance();
    private final static MainController MAIN_CONTROLLER = MainController.getMainController();
    private final static UserRepository USER_REPOSITORY = UserRepository.getInstance();

    private Quiz quiz;

    @FXML
    private Label text_question1, text_question2, text_question3, text_question4, text_question5;

    @FXML
    private Button btn_back, btn_saveInfo;

    @FXML
    private TextField text_answer1, text_answer2, text_answer3, text_answer4, text_answer5;

    @FXML
    private Pane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quiz = new Quiz();

        text_question1.setText("What hobby does your match have?");
        text_question2.setText("What color does your match like?");
        text_question3.setText("Which animal does your match like?");
        text_question4.setText("What motivates your match?");
        text_question5.setText("What is your match's typical saturday night?");

        text_question1.layoutXProperty().bind(pane.widthProperty().subtract(text_question1.widthProperty()).divide(2));
        text_question2.layoutXProperty().bind(pane.widthProperty().subtract(text_question2.widthProperty()).divide(2));
        text_question3.layoutXProperty().bind(pane.widthProperty().subtract(text_question3.widthProperty()).divide(2));
        text_question4.layoutXProperty().bind(pane.widthProperty().subtract(text_question4.widthProperty()).divide(2));
        text_question5.layoutXProperty().bind(pane.widthProperty().subtract(text_question5.widthProperty()).divide(2));

    }

    public void saveInfoButtonPressed(ActionEvent event) {

        USER_REPOSITORY.save(MAIN_CONTROLLER.getRegisteringUser());
        MAIN_CONTROLLER.getRegisteringUser().setUserId(USER_REPOSITORY.getUserId(MAIN_CONTROLLER.getRegisteringUser()));

        //System.out.println(MAIN_CONTROLLER.getRegisteringUser().getUserId());

        quiz.addQuestion(new Question(text_question1.getText(), text_answer1.getText()));
        quiz.addQuestion(new Question(text_question2.getText(), text_answer2.getText()));
        quiz.addQuestion(new Question(text_question3.getText(), text_answer3.getText()));
        quiz.addQuestion(new Question(text_question4.getText(), text_answer4.getText()));
        quiz.addQuestion(new Question(text_question5.getText(), text_answer5.getText()));

        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            quiz.getQuestion(i).setQuestionId(i+1);
        }

        QUIZ_REPOSITORY.save(quiz);

        MAIN_CONTROLLER.removeRegisteringUser();

        MAIN_CONTROLLER.switchSceneLogin(event, btn_saveInfo);
    }
}
