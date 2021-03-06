package hiennguyen.me.architecture.example.features.products.module;


import android.support.v4.app.Fragment;


import dagger.Binds;
import dagger.Module;
import hiennguyen.me.architecture.example.features.base.modules.BaseFragmentModule;
import hiennguyen.me.architecture.example.features.products.ProductsFragment;
import hiennguyen.me.architecture.example.injection.scopes.PerFragment;

@Module(includes = BaseFragmentModule.class)
public abstract class ProductFragmentModule  {

    @Binds
    @PerFragment
    abstract Fragment fragment(ProductsFragment fragment);
}
