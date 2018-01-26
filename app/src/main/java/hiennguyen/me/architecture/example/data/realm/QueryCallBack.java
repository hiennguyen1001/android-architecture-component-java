package hiennguyen.me.architecture.example.data.realm;

import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public interface QueryCallBack<R extends RealmObject> {
    RealmResults<R> onQuery(RealmQuery<R> rRealmQuery);
}
