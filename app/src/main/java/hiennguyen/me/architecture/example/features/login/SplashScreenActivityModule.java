package hiennguyen.me.architecture.example.features.login;

import android.support.v4.app.FragmentActivity;

import dagger.Binds;
import dagger.Module;
import hiennguyen.me.architecture.example.features.base.modules.BaseActivityModule;
import hiennguyen.me.architecture.example.features.login.views.SplashScreenActivity;
import hiennguyen.me.architecture.example.injection.scopes.PerActivity;


@Module(includes = BaseActivityModule.class)
public abstract class SplashScreenActivityModule {

    @Binds
    @PerActivity
    abstract FragmentActivity activity(SplashScreenActivity splashScreenActivity);
}
