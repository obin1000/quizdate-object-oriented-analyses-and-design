package quizdate.controller;
//TODO: Generate random answer possibilities, show score in the end.
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import quizdate.model.*;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MakeQuizController implements Initializable {
    @FXML
    private ImageView profilePicture;
    @FXML
    private Button btn_chat, btn_next;
    @FXML
    private RadioButton btn_answerA, btn_answerB, btn_answerC, btn_answerD;
    @FXML
    private Label lbl_username, lbl_question;

    private Image img = new Image("file:./src/quizdate/images/trump.jpg");
    private static final MainController MAIN_CONTROLLER = MainController.getMainController();
    private static final QuizRepository QUIZ_REPOSITORY = QuizRepository.getInstance();
    private User currentUser = MAIN_CONTROLLER.getCurrentUser();
    private User otherUser = MAIN_CONTROLLER.getMatchedUser();

    private int counter = 0;
    private String[] answerOptions = new String[4];
    private Quiz quiz = otherUser.getQuiz();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (otherUser.getProfilePicture() == null){
            profilePicture.setImage(img);
        }else {
            profilePicture.setImage(otherUser.getProfilePicture());
        }

        lbl_username.setText(otherUser.getFirstName() + " " + otherUser.getLastName());


        setAnswers();

        // TODO : set answers and question to present the correct values.

    }

    public void nextButtonPressed(ActionEvent event){

        if(counter < quiz.getQuestions().size()){
            if (btn_answerA.isSelected()){
                if (btn_answerA.getText().equals(otherUser.getQuiz().getQuestion(counter).getAnswer())) {
                    quiz.addScore();
                }
                quiz.getQuestion(counter).setGivenAnswer(btn_answerA.getText());
                counter++;
            }
            else if (btn_answerB.isSelected()){
                if (btn_answerB.getText().equals(otherUser.getQuiz().getQuestion(counter).getAnswer())) {
                    quiz.addScore();
                }
                quiz.getQuestion(counter).setGivenAnswer(btn_answerB.getText());
                counter++;
            }
            else if (btn_answerC.isSelected()){
                if (btn_answerC.getText().equals(otherUser.getQuiz().getQuestion(counter).getAnswer())) {
                    quiz.addScore();
                }
                quiz.getQuestion(counter).setGivenAnswer(otherUser.getQuiz().getQuestion(counter).getAnswer());
                counter++;
            }
            else if (btn_answerD.isSelected()){
                if (btn_answerD.getText().equals(otherUser.getQuiz().getQuestion(counter).getAnswer())) {
                    quiz.addScore();
                }
                quiz.getQuestion(counter).setGivenAnswer(btn_answerD.getText());
                counter++;
            }
            else {
                System.out.println("NOTHING HAS BEEN SELECTED");
            }
        if (counter >= quiz.getQuestions().size()) {
            MAIN_CONTROLLER.setQuizData(quiz);
            MAIN_CONTROLLER.switchSceneQuizResult(event, btn_next);

            } else {
                setAnswers();
            }
    }
    }

    public void chatButtonPressed(ActionEvent event){
        MAIN_CONTROLLER.switchSceneChat(event,btn_chat);
    }

    private boolean checkAnswers(String answer) {
        for (String answerOption : answerOptions) {
            if (answer.equals(answerOption)) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    private void randomnizeAnswers() {
        answerOptions[0] = otherUser.getQuiz().getQuestion(counter).getAnswer();
        String randomAnswer;
        Random randomnizer = new Random();

        for (int i = 1; i < answerOptions.length; i++) {
            do {
                randomAnswer = QUIZ_REPOSITORY.getRandomAnswer(counter+1).getAnswer();
            } while (!checkAnswers(randomAnswer));
            answerOptions[i] = randomAnswer;
        }

        for (int i = answerOptions.length - 1; i > 0; i--)
        {
            int index = randomnizer.nextInt(i + 1);
            System.out.println(answerOptions[index]);
            String a = answerOptions[index];
            answerOptions[index] = answerOptions[i];
            answerOptions[i] = a;
        }
    }


    private void setAnswers() {
        randomnizeAnswers();
        btn_answerA.setText(answerOptions[0]);
        btn_answerB.setText(answerOptions[1]);
        btn_answerC.setText(answerOptions[2]);
        btn_answerD.setText(answerOptions[3]);
        lbl_question.setText(otherUser.getQuiz().getQuestion(counter).getQuestion());


    }
}

