package hiennguyen.me.architecture.example.features.login.views;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import hiennguyen.me.architecture.R;
import hiennguyen.me.architecture.databinding.ActivitySplashBinding;
import hiennguyen.me.architecture.example.features.base.views.BaseActivity;
import hiennguyen.me.architecture.example.features.home.views.HomeActivity;
import hiennguyen.me.architecture.example.features.login.viewmodels.SplashViewModel;


public class SplashScreenActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

    @Override
    protected int layoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(() -> {
            navigator.startActivity(HomeActivity.class);
            navigator.finishActivity();
        }, 400);
    }
}
