package hiennguyen.me.architecture.example.features.productdetail;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.ArrayList;

import hiennguyen.me.architecture.R;
import hiennguyen.me.architecture.databinding.ActivityProductDetailBinding;
import hiennguyen.me.architecture.example.binding.ColorConverters;
import hiennguyen.me.architecture.example.features.base.views.BaseActivity;
import hiennguyen.me.architecture.example.features.bindingmodels.ProductDetailBindingViewModel;
import hiennguyen.me.architecture.example.features.productdetail.adapters.ProductPhotoPagerAdapter;
import hiennguyen.me.architecture.example.features.productdetail.viewmodels.ProductDetailViewModel;

public class ProductDetailActivity extends BaseActivity<ActivityProductDetailBinding, ProductDetailViewModel> {

    private ProductPhotoPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ColorConverters.setBackgroundColorRes(binding.appbar.toolbar, R.color.white);
        setSupportActionBar(binding.appbar.toolbar);
        binding.appbar.tvAppBarTitle.setText(R.string.product_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        adapter = new ProductPhotoPagerAdapter(this, new ArrayList<>());
        binding.viewPager.setAdapter(adapter);

        binding.btnNotify.setOnClickListener(view -> {
            if (binding.btnNotify.isSelected()) {
                binding.btnNotify.setSelected(false);
                binding.btnNotify.setText(R.string.notify_me);
                ColorConverters.setTextColorRes(binding.btnNotify, R.color.colorPrimary);
                binding.btnNotify.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            } else {
                binding.btnNotify.setSelected(true);
                binding.btnNotify.setText(R.string.subscribed);
                ColorConverters.setTextColorRes(binding.btnNotify, R.color.white);
                binding.btnNotify.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_white_20dp, 0, 0, 0);
            }
        });

        loadProduct();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadProduct() {
        viewModel.loadProduct(1).observe(this, product -> {
            if (product != null) {
                adapter.setImageUrls(product.getImageUrls());
                binding.setProduct(new ProductDetailBindingViewModel(product));
                if (product.isOutOfStock()) {
                    binding.btnAddToCart.setEnabled(false);
                }
                binding.btnAddToCart.setOnClickListener(view ->
                        Toast.makeText(ProductDetailActivity.this, "add to cart clicked", Toast.LENGTH_SHORT).show());
            }
        });
    }

}
