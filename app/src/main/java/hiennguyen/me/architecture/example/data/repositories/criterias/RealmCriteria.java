package hiennguyen.me.architecture.example.data.repositories.criterias;


import io.realm.RealmObject;
import io.realm.RealmQuery;

public class RealmCriteria<R extends RealmObject> extends Criteria {
    private RealmQuery<R> realmQuery;

    public RealmCriteria(RealmQuery<R> realmQuery) {
        this.realmQuery = realmQuery;
    }

    public RealmQuery<R> getRealmQuery() {
        return realmQuery;
    }

    public void setRealmQuery(RealmQuery<R> realmQuery) {
        this.realmQuery = realmQuery;
    }
}
