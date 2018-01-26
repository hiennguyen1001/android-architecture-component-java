package hiennguyen.me.architecture.example.data.repositories;



import java.util.List;

import hiennguyen.me.architecture.example.data.repositories.criterias.Criteria;

public interface Repository<T> {
    void insertOrUpdate(T entity);

    void delete(T entity);

    void delete(Criteria criteria);

    List<T> searchFor(Criteria criteria);

    List<T> getAll();

    T getById(int id);

    void tearDown();
}
