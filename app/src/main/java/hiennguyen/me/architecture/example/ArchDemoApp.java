package hiennguyen.me.architecture.example;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import hiennguyen.me.architecture.BuildConfig;
import hiennguyen.me.architecture.example.injection.components.DaggerAppComponent;
import io.realm.Realm;
import timber.log.Timber;


public class ArchDemoApp extends Application implements HasActivityInjector {

    public static Application app;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Realm.init(this);
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
            LeakCanary.install(this);
        }
    }

    public static Context getAppContext() {
        return app.getApplicationContext();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
