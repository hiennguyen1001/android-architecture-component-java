package hiennguyen.me.architecture.example.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.*;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@com.bumptech.glide.annotation.GlideModule
public class GlideModule extends AppGlideModule {


}
