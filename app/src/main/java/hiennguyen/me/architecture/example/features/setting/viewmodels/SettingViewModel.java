package hiennguyen.me.architecture.example.features.setting.viewmodels;

import android.app.Application;


import javax.inject.Inject;

import hiennguyen.me.architecture.example.features.base.viewmodels.BaseViewModel;

public class SettingViewModel extends BaseViewModel {

    @Inject
    public SettingViewModel(Application application) {
        super(application);
    }
}
