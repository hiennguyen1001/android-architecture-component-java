package hiennguyen.me.architecture.example.features.products.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import java.util.List;

import hiennguyen.me.architecture.example.data.models.realm.CategoriesRes;
import hiennguyen.me.architecture.example.features.products.ProductsFragment;

public class ProductPagerAdapter extends FragmentStatePagerAdapter {

    private List<CategoriesRes.Category> categories;

    public ProductPagerAdapter(FragmentManager fm, List<CategoriesRes.Category> categories) {
        super(fm);
        this.categories = categories;
    }

    @Override
    public Fragment getItem(int position) {
        return ProductsFragment.newInstance();
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).category;
    }

    public void setCategories(List<CategoriesRes.Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }
}
