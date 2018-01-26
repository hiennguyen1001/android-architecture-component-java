package hiennguyen.me.architecture.example.injection.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;


import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import hiennguyen.me.architecture.example.features.base.viewmodels.ViewModelFactory;
import hiennguyen.me.architecture.example.features.cart.viewmodels.CartViewModel;
import hiennguyen.me.architecture.example.features.home.viewmodels.HomeViewModel;
import hiennguyen.me.architecture.example.features.login.viewmodels.SplashViewModel;
import hiennguyen.me.architecture.example.features.products.viewmodels.ProductsViewModel;
import hiennguyen.me.architecture.example.features.setting.viewmodels.SettingViewModel;
import hiennguyen.me.architecture.example.injection.ViewModelKey;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel userViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    abstract ViewModel bindSplashViewModel(SplashViewModel searchViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel.class)
    abstract ViewModel bindSettingViewModel(SettingViewModel repoViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel.class)
    abstract ViewModel bindCardViewModel(CartViewModel cartViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel.class)
    abstract ViewModel bindProductsViewModel(ProductsViewModel productsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
