package hiennguyen.me.architecture.example.features.products.module;

import android.support.v4.app.FragmentActivity;


import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import hiennguyen.me.architecture.example.features.base.modules.BaseActivityModule;
import hiennguyen.me.architecture.example.features.products.ProductsActivity;
import hiennguyen.me.architecture.example.features.products.ProductsFragment;
import hiennguyen.me.architecture.example.injection.scopes.PerActivity;
import hiennguyen.me.architecture.example.injection.scopes.PerFragment;


@Module(includes = BaseActivityModule.class)
public abstract class ProductsActivityModule {

    @Binds
    @PerActivity
    abstract FragmentActivity activity(ProductsActivity productsActivity);

    @PerFragment
    @ContributesAndroidInjector(modules = {ProductFragmentModule.class})
    abstract ProductsFragment bindProductsFragment();
}
