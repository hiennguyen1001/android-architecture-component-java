package hiennguyen.me.architecture.example.features.login.viewmodels;

import android.app.Application;


import javax.inject.Inject;

import hiennguyen.me.architecture.example.features.base.viewmodels.BaseViewModel;

public class SplashViewModel extends BaseViewModel {

    @Inject
    public SplashViewModel(Application application) {
        super(application);

    }
}
