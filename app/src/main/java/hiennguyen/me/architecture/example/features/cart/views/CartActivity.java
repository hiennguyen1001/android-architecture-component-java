package hiennguyen.me.architecture.example.features.cart.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import hiennguyen.me.architecture.R;
import hiennguyen.me.architecture.databinding.ActivityCartBinding;
import hiennguyen.me.architecture.example.features.base.views.BaseActivity;
import hiennguyen.me.architecture.example.features.cart.viewmodels.CartViewModel;


public class CartActivity extends BaseActivity<ActivityCartBinding, CartViewModel> {

    @Override
    protected int layoutId() {
        return R.layout.activity_cart;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.layoutToolbar.tvAppBarTitle.setText(R.string.cart);
        setSupportActionBar(binding.layoutToolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mFragmentManager.beginTransaction().replace(R.id.fl_content, new CartFragment()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
