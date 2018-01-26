package hiennguyen.me.architecture.example.injection.modules;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import hiennguyen.me.architecture.BuildConfig;
import hiennguyen.me.architecture.example.data.models.realm.RAddress;
import hiennguyen.me.architecture.example.data.repositories.RealmRepository;
import hiennguyen.me.architecture.example.data.repositories.RealmRepositoryImpl;
import hiennguyen.me.architecture.example.data.repositories.remote.ProductRepository;
import hiennguyen.me.architecture.example.data.services.NetworkingHelper;
import hiennguyen.me.architecture.example.utils.Config;
import io.realm.RealmConfiguration;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import timber.log.Timber;

@Module
public class NetModule {

    @Provides
    static Gson provideGson() {
        return new GsonBuilder()
                // Custom type adapters for models are not needed when using Gson, but this
                // type adapter is a good example if you want to write one yourself.
                .create();
    }

    @Provides
    static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, StethoInterceptor stethoInterceptor) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(loggingInterceptor);
            httpClient.addNetworkInterceptor(stethoInterceptor);
        }
        return httpClient.build();
    }


    @Provides
    static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> {
            Timber.tag(Config.API_LOG);
            Timber.d(message);
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    static StethoInterceptor provideStetho() {
        return new StethoInterceptor();
    }

    @Provides
    static Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(Config.API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .callFactory(okHttpClient);
    }

    @Provides
    NetworkingHelper provideNetworkingHelper(Context context, Retrofit.Builder builder) {
        return new NetworkingHelper(context, builder);
    }

    @Provides
    RealmRepository<RAddress> provideRealmRepositoryAddress(RealmConfiguration realmConfiguration) {
        return new RealmRepositoryImpl<>(RAddress.class, realmConfiguration);
    }

    @Provides
    ProductRepository provideProductRepositories(NetworkingHelper networkingHelper) {
        return new ProductRepository(networkingHelper);
    }
}
