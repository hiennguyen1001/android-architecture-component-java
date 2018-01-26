package hiennguyen.me.architecture.example.data.repositories;


import java.util.List;

import hiennguyen.me.architecture.example.data.realm.QueryCallBack;
import hiennguyen.me.architecture.example.data.repositories.criterias.Criteria;
import hiennguyen.me.architecture.example.data.repositories.criterias.RealmCriteria;
import io.reactivex.Flowable;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmRepositoryImpl<R extends RealmObject> implements RealmRepository<R> {

    Realm realm;
    private Class<R> clazz;
    private RealmConfiguration realmConfiguration;

    public RealmRepositoryImpl(Class<R> clazz, RealmConfiguration realmConfiguration) {
        this.clazz = clazz;
        this.realmConfiguration = realmConfiguration;
        this.realm = Realm.getInstance(realmConfiguration);
    }

    @Override
    public void insertOrUpdate(R entity) {
        realm.executeTransactionAsync(realm -> realm.insertOrUpdate(entity));
    }

    @Override
    public void delete(R entity) {
        throw new UnsupportedOperationException("RealmRepository does not support this operation");
    }

    @Override
    public void delete(Criteria criteria) {
        if (criteria instanceof RealmCriteria) {
            realm.executeTransactionAsync(realm ->
                    ((RealmCriteria) criteria).getRealmQuery().findAll().deleteAllFromRealm()
            );
        } else {
            throw new IllegalArgumentException("Must use RealmCriteria for this repository");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<R> searchFor(Criteria criteria) {
        if (criteria instanceof RealmCriteria) {
            return ((RealmCriteria<R>) criteria).getRealmQuery().findAll();
        }

        throw new IllegalArgumentException("Must use RealmCriteria for this repository");
    }

    @Override
    public List<R> getAll() {
        return realm.where(clazz).findAll();
    }

    @Override
    public R getById(int id) {
        return realm.where(clazz).equalTo("id", id).findFirst();
    }

    @Override
    public void tearDown() {
        if (!realm.isClosed()) {
            realm.close();
        }
    }

    @Override
    public RealmQuery<R> createRealmQuery() {
        return realm.where(clazz);
    }

    @Override
    public RealmResults<R> getRealmResults(RealmCriteria<R> criteria) {
        return criteria.getRealmQuery().findAll();
    }

    @Override
    public RealmCriteria<R> createCriteria() {
        return new RealmCriteria<>(createRealmQuery());
    }

    @Override
    public Flowable<List<R>> queryRealm(QueryCallBack<R> callBack) {
        return callBack.onQuery(realm.where(clazz)).asFlowable()
                .map(rs -> realm.copyFromRealm(rs));
    }

    @Override
    public R getOne() {
        R r = realm.where(clazz).findFirst();
        return r;
    }

    @Override
    public void insertOrUpdateList(List<R> list) {
        realm.executeTransactionAsync(realm1 -> {
            for (R t : list) realm1.insertOrUpdate(t);
        });
    }

}
