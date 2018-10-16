package quizdate.model;

import java.util.List;

public interface Repository<T> {

    List<T> getAll();

    T get(int id);

    boolean save(T t);

    boolean update(int id, T t);

    boolean remove(int id);



}
