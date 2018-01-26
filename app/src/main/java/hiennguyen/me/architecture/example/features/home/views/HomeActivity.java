package hiennguyen.me.architecture.example.features.home.views;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import java.util.Random;

import hiennguyen.me.architecture.R;
import hiennguyen.me.architecture.databinding.ActivityHomeBinding;
import hiennguyen.me.architecture.example.data.models.realm.RAddress;
import hiennguyen.me.architecture.example.features.actionhandlers.LongToaster;
import hiennguyen.me.architecture.example.features.base.views.BaseActivity;
import hiennguyen.me.architecture.example.features.cart.views.CartActivity;
import hiennguyen.me.architecture.example.features.delegates.Toaster;
import hiennguyen.me.architecture.example.features.home.viewmodels.HomeViewModel;
import hiennguyen.me.architecture.example.features.products.ProductsActivity;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(binding.layoutToolbar.toolbar);

        binding.fab.setOnClickListener(view -> {
            RAddress address = new RAddress();
            address.setId(new Random().nextInt(2222));
            address.setAddress1("Address " + new Random().nextInt(2222));
            address.setAddress2("City " + new Random().nextInt(2222));
            viewModel.addAddress(address);

            showToast(getActivity(), "Added RAddress");

        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,
                new HomeFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_card) {
            navigator.startActivity(CartActivity.class);
        } else if(item.getItemId() == R.id.action_product) {
            navigator.startActivity(ProductsActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected Toaster getToaster() {
        return new LongToaster();
    }
}
