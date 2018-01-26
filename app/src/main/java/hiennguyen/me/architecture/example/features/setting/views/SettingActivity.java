package hiennguyen.me.architecture.example.features.setting.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import hiennguyen.me.architecture.R;
import hiennguyen.me.architecture.databinding.ActivitySettingBinding;
import hiennguyen.me.architecture.example.features.base.views.BaseActivity;
import hiennguyen.me.architecture.example.features.setting.viewmodels.SettingViewModel;


public class SettingActivity extends BaseActivity<ActivitySettingBinding, SettingViewModel> {

    @Override
    protected int layoutId() {
        return R.layout.activity_setting;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(binding.layoutToolbar.toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
