package hiennguyen.me.architecture.example.features.base.modules;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import hiennguyen.me.architecture.example.injection.modules.ViewModelModule;
import hiennguyen.me.architecture.example.injection.qualifiers.ActivityContext;
import hiennguyen.me.architecture.example.injection.qualifiers.ActivityFragmentManager;
import hiennguyen.me.architecture.example.injection.scopes.PerActivity;
import hiennguyen.me.architecture.example.core.navigators.ActivityNavigator;
import hiennguyen.me.architecture.example.core.navigators.Navigator;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public abstract class BaseActivityModule {

    static final String ACTIVITY_FRAGMENT_MANAGER = "BaseActivityModule.activityFragmentManager";

    @Binds
    @PerActivity
    @ActivityContext
    abstract Context provideActivityContext(FragmentActivity activity);

    @Provides
    @PerActivity
    @ActivityContext
    static FragmentActivity provideFragmentActivity(FragmentActivity activity) {
        return activity;
    }

    @Provides
    @PerActivity
    @ActivityFragmentManager
    static FragmentManager provideFragmentManager(FragmentActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @Binds
    @PerActivity
    abstract Navigator provideActivityNavigator(ActivityNavigator navigator);


}
