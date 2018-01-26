package hiennguyen.me.architecture.example.data.repositories;


import java.util.ArrayList;
import java.util.List;

import hiennguyen.me.architecture.example.data.models.Model;
import hiennguyen.me.architecture.example.data.repositories.criterias.Criteria;

public class SimpleRepository<T extends Model> implements Repository<T> {
    private List<T> data;

    public SimpleRepository() {
        data = new ArrayList<>();
    }

    @Override
    public void insertOrUpdate(T entity) {
        if (!data.contains(entity)) {
            data.add(entity);
        }
    }

    @Override
    public void delete(T entity) {
        data.remove(entity);
    }

    @Override
    public void delete(Criteria criteria) {

    }

    @Override
    public List<T> searchFor(Criteria criteria) {
        return null;
    }

    @Override
    public List<T> getAll() {
        return data;
    }

    @Override
    public T getById(int id) {
        return null;
    }

    @Override
    public void tearDown() {

    }
}
