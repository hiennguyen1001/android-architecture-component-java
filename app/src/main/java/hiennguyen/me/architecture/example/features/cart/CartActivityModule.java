package hiennguyen.me.architecture.example.features.cart;


import android.support.v4.app.FragmentActivity;


import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import hiennguyen.me.architecture.example.features.base.modules.BaseActivityModule;
import hiennguyen.me.architecture.example.features.cart.views.CartActivity;
import hiennguyen.me.architecture.example.features.cart.views.CartFragment;
import hiennguyen.me.architecture.example.injection.scopes.PerActivity;
import hiennguyen.me.architecture.example.injection.scopes.PerFragment;

@Module(includes = BaseActivityModule.class)
public abstract class CartActivityModule {


    @Binds
    @PerActivity
    abstract FragmentActivity activity(CartActivity cartActivity);

    @PerFragment
    @ContributesAndroidInjector(modules = {CartFragmentModule.class})
    abstract CartFragment bindCardFragment();
}