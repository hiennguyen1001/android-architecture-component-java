package hiennguyen.me.architecture.example.data.repositories.remote;

import com.google.gson.GsonBuilder;

import hiennguyen.me.architecture.example.data.services.NetworkingHelper;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class RemoteRepositories<T> {

    protected Scheduler observerSchedule;
    protected final T mRestApi;
    NetworkingHelper networkingHelper;

    protected RemoteRepositories(Class<T> serviceClass, NetworkingHelper networkingHelper) {
        this.networkingHelper = networkingHelper;
        mRestApi = this.networkingHelper.createServiceImplemetation(serviceClass, getCustomGsonBuilder().create());
        observerSchedule = AndroidSchedulers.mainThread();
    }

    protected GsonBuilder getCustomGsonBuilder() {
        return networkingHelper.getDefautlGsonBuilder();
    }
}
