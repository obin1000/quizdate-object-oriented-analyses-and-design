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
    private static final UserRepository USER_REPOSITORY = UserRepository.getInstance();
    private static final AnswerRepository ANSWER_REPOSITORY = AnswerRepository.getInstance();
    private static final MatchService MATCH_SERVICE = MatchService.getInstance();
    private User currentUser = MAIN_CONTROLLER.getCurrentUser();
    private User otherUser = MAIN_CONTROLLER.getMatchedUser();

    private int counter = 1;
    private int score = 0;
    private String[] answerOptions = new String[4];
    private Quiz quiz;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (otherUser.getProfilePicture() == null){
            profilePicture.setImage(img);
        }else {
            profilePicture.setImage(otherUser.getProfilePicture());
        }

        quiz = new Quiz();

        lbl_username.setText(otherUser.getFirstName() + " " + otherUser.getLastName());

        setAnswers();

        // TODO : set answers and question to present the correct values.

    }

    public void nextButtonPressed(ActionEvent event){

        if(counter <= 5){
            if (btn_answerA.isSelected()){
                if (btn_answerA.getText().equals(ANSWER_REPOSITORY.get(counter).getRightAnswer())) {
                    score++;
                }
                quiz.addAnswer(new Answer(counter, ANSWER_REPOSITORY.get(counter).getRightAnswer(),
                        btn_answerA.getText()));
                quiz.addQuestion(ANSWER_REPOSITORY.getQuestion(counter));
                counter++;
            }
            else if (btn_answerB.isSelected()){
                if (btn_answerB.getText().equals(ANSWER_REPOSITORY.get(counter).getRightAnswer())) {
                    score++;
                }
                quiz.addAnswer(new Answer(counter, ANSWER_REPOSITORY.get(counter).getRightAnswer(),
                        btn_answerB.getText()));
                quiz.addQuestion(ANSWER_REPOSITORY.getQuestion(counter));
                counter++;
            }
            else if (btn_answerC.isSelected()){
                if (btn_answerC.getText().equals(ANSWER_REPOSITORY.get(counter).getRightAnswer())) {
                    score++;
                }
                quiz.addAnswer(new Answer(counter, ANSWER_REPOSITORY.get(counter).getRightAnswer(),
                        btn_answerC.getText()));
                quiz.addQuestion(ANSWER_REPOSITORY.getQuestion(counter));
                counter++;
            }
            else if (btn_answerD.isSelected()){
                if (btn_answerD.getText().equals(ANSWER_REPOSITORY.get(counter).getRightAnswer())) {
                    score++;
                }
                quiz.addAnswer(new Answer(counter, ANSWER_REPOSITORY.get(counter).getRightAnswer(),
                        btn_answerD.getText()));
                quiz.addQuestion(ANSWER_REPOSITORY.getQuestion(counter));
                counter++;
            }
            else {
                System.out.println("NOTHING HAS BEEN SELECTED");
            }
            if (counter > 5) {
                MAIN_CONTROLLER.switchSceneLogin(event, btn_next);
                System.out.println(score);
                System.out.println(quiz.getAnswers());
                System.out.println(quiz.getQuestions());
            } else {
                setAnswers();
            }
    }
    }

    public void chatButtonPressed(ActionEvent event){
        MAIN_CONTROLLER.switchSceneChat(event,btn_chat);
        //TODO : Zorgen dat de usergegevens via ChatController achterhaald kunnen worden.
        //TODO : Wanneer bovenstaand compleet is, kan onderstaan geuncomment worden
//        User reset = null;
//        MAIN_CONTROLLER.setMatchedUser(reset);
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
        answerOptions[0] = ANSWER_REPOSITORY.get(counter).getRightAnswer();
        String randomAnswer;
        Random randomnizer = new Random();

        for (int i = 1; i < answerOptions.length; i++) {
            do {
                randomAnswer = ANSWER_REPOSITORY.getRandomAnswer(counter).getRightAnswer();
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
        lbl_question.setText(ANSWER_REPOSITORY.getQuestion(counter));
    }
}

