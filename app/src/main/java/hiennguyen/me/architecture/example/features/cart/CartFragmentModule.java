package hiennguyen.me.architecture.example.features.cart;


import android.support.v4.app.Fragment;


import dagger.Binds;
import dagger.Module;
import hiennguyen.me.architecture.example.features.base.modules.BaseFragmentModule;
import hiennguyen.me.architecture.example.features.cart.views.CartFragment;
import hiennguyen.me.architecture.example.injection.scopes.PerFragment;


@Module(includes = BaseFragmentModule.class)
public abstract class CartFragmentModule {

    @Binds
    @PerFragment
    abstract Fragment fragment(CartFragment mainFragment);
}