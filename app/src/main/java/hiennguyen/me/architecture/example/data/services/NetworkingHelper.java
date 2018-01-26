package hiennguyen.me.architecture.example.data.services;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkingHelper {

    public static final String USER_AGENT = "User-Agent";
    public static final String X_UID = "x-uid";

    private static Context mContext;
    private static Retrofit.Builder mDefaultBuilder;
    private static GsonBuilder mDefaultGsonBuilder;

    public NetworkingHelper(Context context, Retrofit.Builder builder) {
        mContext = context;
        mDefaultBuilder = builder;
        mDefaultGsonBuilder = Lazy.GSON_INSTANCE;
    }

    public <T> T createServiceImplemetation(Class<T> clazz) {
        return mDefaultBuilder.
                addConverterFactory(GsonConverterFactory.create(mDefaultGsonBuilder.create()))
                .build().create(clazz);
    }

    public <T> T createServiceImplemetation(Class<T> clazz, Gson gson) {
        return mDefaultBuilder.
                addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(clazz);
    }

    public static GsonBuilder getDefautlGsonBuilder() {
        return mDefaultGsonBuilder;
    }

    private static class Lazy {
        private static final GsonBuilder GSON_INSTANCE = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter());
    }
}
