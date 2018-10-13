package quizdate.model;

import quizdate.controller.MainController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AnswerRepository implements DAO<Answer> {

    private final static AnswerRepository singleton;
    private final static MainController MAIN_CONTROLLER = MainController.getMainController();
    private final static UserRepository USER_REPOSITORY = UserRepository.getInstance();

    static {
        singleton = new AnswerRepository();
    }

    private AnswerRepository() {
    }

    @Override
    public List<Answer> getAll() {
        return null;
    }


    @Override
    public Answer get(int id) {
        Answer questionandanswer = null;
        try {

            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Answer WHERE idQuestion = " + id + " AND userId = "
            +  MAIN_CONTROLLER.getMatchedUser().getUserId());

            if(rs.next()) {
                questionandanswer = new Answer(id, rs.getString("rightAnswer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return questionandanswer;
    }

    public String getQuestion(int id) {
        String question = null;
        try {

            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Question WHERE idQuestion = " + id);

            if(rs.next()) {
                question = rs.getString("question");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return question;
    }

    @Override
    public boolean save(Answer question) {
        boolean status = false;

        try {
            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            statement.executeUpdate("INSERT INTO Answer() VALUES ('" + question.getRightAnswer()  + "', " +
                    MainController.getMainController().getCurrentUser().getUserId() + "," +  1
                    +  "  )");
            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return status;
    }

    @Override
    public boolean update(int id, Answer question) {
        boolean status = false;

        try {
            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            statement.executeUpdate("UPDATE Answer SET rightAnswer = '" + question.getRightAnswer() +
                    "' WHERE idQuestion = " + question.getQuestionId() + " AND userId = " +  id);

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

    public static AnswerRepository getInstance() {
        return AnswerRepository.singleton;
    }

}
