package hiennguyen.me.architecture.example.features.base.views;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;


import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import hiennguyen.me.architecture.example.core.navigators.Navigator;
import hiennguyen.me.architecture.example.features.actionhandlers.SimpleToaster;
import hiennguyen.me.architecture.example.features.base.viewmodels.BaseViewModel;
import hiennguyen.me.architecture.example.features.delegates.Toaster;
import hiennguyen.me.architecture.example.injection.qualifiers.ActivityFragmentManager;


public abstract class BaseActivity<V extends ViewDataBinding, M extends BaseViewModel> extends DaggerAppCompatActivity implements Toaster {
    protected V binding;
    protected M viewModel;

    @Inject
    protected Navigator navigator;

    @ActivityFragmentManager
    @Inject
    protected FragmentManager mFragmentManager;

    @Inject
    ViewModelProvider.Factory mFactory;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = bindingView(layoutId());
        viewModel = ViewModelProviders.of(this, mFactory).get(viewModelClass());
    }

    protected V bindingView(int layoutId) {
        return DataBindingUtil.setContentView(this, layoutId);
    }

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @SuppressWarnings("unchecked")
    private Class<M> viewModelClass() {
        // dirty hack to get generic type https://stackoverflow.com/a/1901275/719212
        return (Class<M>)
                ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[1];
    }

    protected Activity getActivity() {
        return this;
    }

    @Override
    public void showToast(Context context, String message) {
        getToaster().showToast(context, message);
    }

    protected Toaster getToaster() {
        return new SimpleToaster();
    }

}
