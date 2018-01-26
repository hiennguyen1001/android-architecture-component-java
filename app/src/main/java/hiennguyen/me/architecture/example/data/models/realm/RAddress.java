package hiennguyen.me.architecture.example.data.models.realm;


import hiennguyen.me.architecture.example.data.models.Model;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class RAddress extends RealmObject implements Model {

    @PrimaryKey
    private int id;
    private String address1;
    private String address2;

    public RAddress() {

    }

    public RAddress(int id, String address1, String address2) {
        this.id = id;
        this.address1 = address1;
        this.address2 = address2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Override
    public boolean areItemsThemSameWith(Model model) {
        if(model instanceof RAddress) {
            return ((RAddress) model).id == id;
        }
        return false;
    }

    @Override
    public boolean areContentsThemSameWith(Model model) {
        if(model instanceof RAddress) {
            return ((RAddress) model).address1.equals(address1) && ((RAddress) model).address2.equals(address2);
        }
        return false;
    }
}
