package quizdate.model;

import quizdate.controller.MainController;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizRepository implements Repository<Quiz> {

    private final static QuizRepository singleton;
    private final static UserRepository USER_REPOSITORY = UserRepository.getInstance();

    static {
        singleton = new QuizRepository();
    }

    private QuizRepository() {
    }

    @Override
    public List<Quiz> getAll() {
        return null;
    }

    public Question getRandomAnswer(int id) {
        Question question = null;

        try {

            PreparedStatement statement;
            statement = dbConnection.getInstance().prepare("SELECT answer FROM PossibleAnswer " +
                    "WHERE idQuestion = ? ORDER BY RAND() LIMIT 1");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                question = new Question(id, rs.getString("answer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return question;
    }


    @Override
    public Quiz get(int id) {

        Quiz quiz = new Quiz();
        PreparedStatement statement;
        ResultSet rs;

        Question question;

        try {

            statement = dbConnection.getInstance().prepare("SELECT * FROM Question");

            rs = statement.executeQuery();

            while (rs.next()) {
                question = new Question();
                question.setQuestionId(rs.getInt("idQuestion"));
                question.setQuestion(rs.getString("question"));
                quiz.addQuestion(question);
            }


            System.out.println(quiz.getQuestion(2).getQuestion());

            for (int i = 0; i < quiz.getQuestions().size(); i++) {
                statement = dbConnection.getInstance().prepare("SELECT rightAnswer FROM Answer WHERE userId = ? " +
                        "AND idQuestion = ?");
                statement.setInt(1, id);
                statement.setInt(2, quiz.getQuestions().get(i).getQuestionId());

                rs = statement.executeQuery();

                if (rs.next()) {
                    quiz.getQuestions().get(i).setAnswer(rs.getString("rightAnswer"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return quiz;
    }


    @Override
    public boolean save(Quiz quiz) {
        boolean status = false;
        PreparedStatement statement;
        System.out.println(MainController.getMainController().getRegisteringUser().getAdres());

        try {
            for (int i = 0; i < quiz.getQuestions().size(); i++) {
                statement = dbConnection.getInstance().prepare("INSERT INTO Answer VALUES (?, ?, ? )");
                statement.setString(1, quiz.getQuestions().get(i).getAnswer());
                statement.setInt(2, MainController.getMainController().getRegisteringUser().getUserId());
                statement.setInt(3, (quiz.getQuestions().get(i).getQuestionId()));
                statement.executeUpdate();
            }
            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return status;
    }

    @Override
    public boolean update(int id, Quiz quiz) {
        boolean status = false;
        PreparedStatement statement;

        try {
            for (int i = 0; i < quiz.getQuestions().size(); i++) {
                statement = dbConnection.getInstance().prepare("UPDATE Question SET rightAnswer = ?" +
                        " WHERE idQuestion = ? AND userId = ?");
                statement.setString(1, quiz.getQuestions().get(i).getAnswer());
                statement.setInt(2, (quiz.getQuestions().get(i).getQuestionId()));
                statement.setInt(3, id);
                statement.executeUpdate();
            }

            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return status;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    public static QuizRepository getInstance() {
        return QuizRepository.singleton;
    }

}
