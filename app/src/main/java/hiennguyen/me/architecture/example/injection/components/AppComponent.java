package hiennguyen.me.architecture.example.injection.components;

import android.app.Application;


import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import hiennguyen.me.architecture.example.ArchDemoApp;
import hiennguyen.me.architecture.example.injection.modules.AppModule;
import hiennguyen.me.architecture.example.injection.modules.BuilderModule;
import hiennguyen.me.architecture.example.injection.modules.NetModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        NetModule.class,
        BuilderModule.class
})
public interface AppComponent {
    @Component.Builder
        interface Builder {
            @BindsInstance
            Builder application(Application application);
            AppComponent build();
        }
        void inject(ArchDemoApp app);

    }
