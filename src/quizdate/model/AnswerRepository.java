package quizdate.model;

import quizdate.controller.MainController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AnswerRepository implements Repository<Answer> {

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

    public Answer getRandomAnswer(int id) {
        Answer answer = null;

        try {

            PreparedStatement statement;
            statement = dbConnection.getInstance().prepare("SELECT answer FROM PossibleAnswer " +
                    "WHERE idQuestion = ? ORDER BY RAND() LIMIT 1");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                answer = new Answer(id, rs.getString("answer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return answer;
    }


    @Override
    public Answer get(int id) {
        Answer answer = null;
        try {

            PreparedStatement statement;
            statement = dbConnection.getInstance().prepare("SELECT * FROM Answer WHERE idQuestion = ? " +
                            "AND userId = ?");
            statement.setInt(1, id);
            statement.setInt(2, MAIN_CONTROLLER.getMatchedUser().getUserId());

            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                answer = new Answer(id, rs.getString("rightAnswer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return answer;
    }

    public String getQuestion(int id) {
        String question = null;
        try {

            PreparedStatement statement;
            statement = dbConnection.getInstance().prepare("SELECT * FROM Question WHERE idQuestion = ?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

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
    public boolean save(Answer answer) {
        boolean status = false;

        try {
            PreparedStatement statement;
            statement = dbConnection.getInstance().prepare("INSERT INTO Answer VALUES (?, ?, ? )");
            statement.setString(1, answer.getAnswer());
            statement.setInt(2, MAIN_CONTROLLER.getCurrentUser().getUserId());
            statement.setInt(3, answer.getQuestionId());
            statement.executeUpdate();
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
            PreparedStatement statement;
            statement = dbConnection.getInstance().prepare("UPDATE Answer SET rightAnswer = ?" +
                    " WHERE idQuestion = ? AND userId = ?");
            statement.setString(1, question.getAnswer());
            statement.setInt(2, question.getQuestionId());
            statement.setInt(3, id);
            statement.executeUpdate();

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
