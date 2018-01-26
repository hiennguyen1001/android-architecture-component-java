package hiennguyen.me.architecture.example.injection.modules;



import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import hiennguyen.me.architecture.example.features.cart.CartActivityModule;
import hiennguyen.me.architecture.example.features.cart.views.CartActivity;
import hiennguyen.me.architecture.example.features.home.HomeActivityModule;
import hiennguyen.me.architecture.example.features.home.views.HomeActivity;
import hiennguyen.me.architecture.example.features.login.SplashScreenActivityModule;
import hiennguyen.me.architecture.example.features.login.views.SplashScreenActivity;
import hiennguyen.me.architecture.example.features.productdetail.ProductDetailActivity;
import hiennguyen.me.architecture.example.features.productdetail.ProductDetailsModule;
import hiennguyen.me.architecture.example.features.products.ProductsActivity;
import hiennguyen.me.architecture.example.features.products.module.ProductsActivityModule;
import hiennguyen.me.architecture.example.features.setting.SettingsActivityModule;
import hiennguyen.me.architecture.example.features.setting.views.SettingActivity;
import hiennguyen.me.architecture.example.injection.scopes.PerActivity;

@Module
public abstract class BuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {HomeActivityModule.class})
    abstract HomeActivity bindHomeActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = {SplashScreenActivityModule.class})
    abstract SplashScreenActivity bindSplashScreenActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = { SettingsActivityModule.class})
    abstract SettingActivity bindSettingsActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = { CartActivityModule.class})
    abstract CartActivity bindCartActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = { ProductsActivityModule.class})
    abstract ProductsActivity bindProductsActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = { ProductDetailsModule.class})
    abstract ProductDetailActivity bindProductDetailsActivity();

}
