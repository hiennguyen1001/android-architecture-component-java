package hiennguyen.me.architecture.example.features.home;

import android.support.v4.app.Fragment;


import dagger.Binds;
import dagger.Module;
import hiennguyen.me.architecture.example.features.base.modules.BaseFragmentModule;
import hiennguyen.me.architecture.example.features.home.views.HomeFragment;
import hiennguyen.me.architecture.example.injection.scopes.PerFragment;


@Module(includes = BaseFragmentModule.class)
public abstract class HomeFragmentModule {

    @Binds
    @PerFragment
    abstract Fragment fragment(HomeFragment mainFragment);
}
