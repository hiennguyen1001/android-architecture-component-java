package hiennguyen.me.architecture.example.data.repositories;



import java.util.List;

import hiennguyen.me.architecture.example.data.realm.QueryCallBack;
import hiennguyen.me.architecture.example.data.repositories.criterias.RealmCriteria;
import io.reactivex.Flowable;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public interface RealmRepository<R extends RealmObject> extends Repository<R> {

    RealmQuery<R> createRealmQuery();

    RealmResults<R> getRealmResults(RealmCriteria<R> criteria);

    RealmCriteria<R> createCriteria();

    Flowable<List<R>> queryRealm(QueryCallBack<R> callBack);

    R getOne();

    void insertOrUpdateList(List<R> list);



}
