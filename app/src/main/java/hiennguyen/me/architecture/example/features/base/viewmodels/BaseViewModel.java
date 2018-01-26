package hiennguyen.me.architecture.example.features.base.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import hiennguyen.me.architecture.example.ArchDemoApp;


public class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(Application application) {
        super(application);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArchDemoApp getApplication() {
        return (ArchDemoApp) super.getApplication();
    }
}
