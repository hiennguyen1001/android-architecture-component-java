package hiennguyen.me.architecture.example.features.home.viewmodels;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;


import java.util.List;

import javax.inject.Inject;

import hiennguyen.me.architecture.example.data.models.realm.RAddress;
import hiennguyen.me.architecture.example.data.repositories.RealmRepository;
import hiennguyen.me.architecture.example.features.base.viewmodels.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class HomeViewModel extends BaseViewModel {

    public static final String TAG = HomeViewModel.class.getSimpleName();

    private final RealmRepository<RAddress> addressRepository;
    private MutableLiveData<List<RAddress>> observersAddresses = new MutableLiveData<>();
    private CompositeDisposable mDisposable;

    @Inject
    public HomeViewModel(Application application, RealmRepository<RAddress> addressRepository) {
        super(application);
        this.addressRepository = addressRepository;
        mDisposable = new CompositeDisposable();

        mDisposable.add(this.addressRepository.queryRealm(query -> query.findAll())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addresses -> observersAddresses.setValue(addresses), throwable -> throwable.printStackTrace()));

    }

    @Override
    protected void onCleared() {
        if (addressRepository != null) {
            addressRepository.tearDown();
        }
        mDisposable.clear();
        super.onCleared();
    }

    public MutableLiveData<List<RAddress>> getAddresses() {
        return observersAddresses;
    }

    public void addAddress(RAddress address) {
        addressRepository.insertOrUpdate(address);
    }
}
