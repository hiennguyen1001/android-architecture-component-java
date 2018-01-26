package hiennguyen.me.architecture.example.features.products;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.ArrayList;

import hiennguyen.me.architecture.R;
import hiennguyen.me.architecture.databinding.ActivityProductsBinding;
import hiennguyen.me.architecture.example.features.base.views.BaseActivity;
import hiennguyen.me.architecture.example.features.products.adapters.ProductPagerAdapter;
import hiennguyen.me.architecture.example.features.products.viewmodels.ProductsViewModel;

public class ProductsActivity extends BaseActivity<ActivityProductsBinding, ProductsViewModel>
        implements ProductsFragment.OnProductsFragmentInteractionListener {

    private ProductPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(binding.appbar.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        binding.appbar.tvAppBarTitle.setText(R.string.products);
        setUpViewPager();
    }

    private void setUpViewPager() {
        pagerAdapter = new ProductPagerAdapter(mFragmentManager, new ArrayList<>());
        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        viewModel.loadCategories().observe(this, strings -> pagerAdapter.setCategories(strings));


    }

    @Override
    protected int layoutId() {
        return R.layout.activity_products;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_go_to_cart:
                Toast.makeText(this, "go to cart", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
