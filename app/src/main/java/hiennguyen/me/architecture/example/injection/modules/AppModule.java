package hiennguyen.me.architecture.example.injection.modules;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hiennguyen.me.architecture.example.data.realm.RealmModules;
import hiennguyen.me.architecture.example.utils.Config;
import io.realm.RealmConfiguration;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideAppContext(Application app) {
        return app;
    }

    @Provides
    @Singleton
    Resources provideResources(Application app) {
        return app.getResources();
    }

    @Provides
    @Singleton
    RealmConfiguration provideConfiguration() {
        return new RealmConfiguration.Builder()
                .name(Config.DATABASE_NAME)
                .schemaVersion(Config.DATABASE_VERSION)
                .modules(new RealmModules())
                .deleteRealmIfMigrationNeeded()
                .build();
    }
}
