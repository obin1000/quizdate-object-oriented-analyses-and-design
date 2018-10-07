package quizdate.model;

import java.util.List;

public interface Database<T> {

    List<T> getAll();

    void get();

    void edit();

    void save(T t);

    void update(int id, T t);

    void remove(int id);
    


}
