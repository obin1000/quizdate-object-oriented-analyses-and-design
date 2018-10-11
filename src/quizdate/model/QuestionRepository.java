package quizdate.model;

import java.util.List;

public class QuestionRepository implements DAO<Question> {

    @Override
    public List<Question> getAll() {
        return null;
    }

    @Override
    public int getRandomId() {
        return 0;
    }

    @Override
    public Question get(int id) {
        return null;
    }

    @Override
    public boolean save(Question question) {
        return false;
    }

    @Override
    public boolean update(int id, Question question) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
