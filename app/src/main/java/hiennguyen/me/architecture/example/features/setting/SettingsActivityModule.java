package hiennguyen.me.architecture.example.features.setting;

import android.support.v4.app.FragmentActivity;


import dagger.Binds;
import dagger.Module;
import hiennguyen.me.architecture.example.features.base.modules.BaseActivityModule;
import hiennguyen.me.architecture.example.features.setting.views.SettingActivity;
import hiennguyen.me.architecture.example.injection.scopes.PerActivity;


@Module(includes = BaseActivityModule.class)
public abstract class SettingsActivityModule {

    @Binds
    @PerActivity
    abstract FragmentActivity activity(SettingActivity settingActivity);
}
