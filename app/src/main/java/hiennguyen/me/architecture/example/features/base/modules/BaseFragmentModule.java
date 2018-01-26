package hiennguyen.me.architecture.example.features.base.modules;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import hiennguyen.me.architecture.example.core.navigators.ChildFragmentNavigator;
import hiennguyen.me.architecture.example.core.navigators.FragmentNavigator;
import hiennguyen.me.architecture.example.injection.scopes.PerFragment;

@Module
public abstract class BaseFragmentModule {

    public static final String FRAGMENT = "BaseFragmentModule.fragment";

    static final String CHILD_FRAGMENT_MANAGER = "BaseFragmentModule.childFragmentManager";


    @Binds
    @PerFragment
    abstract FragmentNavigator provideFragmentNavigator(ChildFragmentNavigator fragmentNavigator);


    @Provides
    @PerFragment
    static FragmentManager childFragmentManager(Fragment fragment) {
        return fragment.getChildFragmentManager();
    }

}
