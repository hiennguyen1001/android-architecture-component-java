package hiennguyen.me.architecture.example.features.home;

import android.support.v4.app.FragmentActivity;


import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import hiennguyen.me.architecture.example.features.base.modules.BaseActivityModule;
import hiennguyen.me.architecture.example.features.home.views.HomeActivity;
import hiennguyen.me.architecture.example.features.home.views.HomeFragment;
import hiennguyen.me.architecture.example.injection.scopes.PerActivity;
import hiennguyen.me.architecture.example.injection.scopes.PerFragment;


@Module(includes = BaseActivityModule.class)
public abstract class HomeActivityModule {


    @Binds
    @PerActivity
    abstract FragmentActivity activity(HomeActivity homeActivity);

    @PerFragment
    @ContributesAndroidInjector(modules = {HomeFragmentModule.class})
    abstract HomeFragment bindHomeFragment();
}
