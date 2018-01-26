package hiennguyen.me.architecture.example.features.base.views;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import hiennguyen.me.architecture.example.core.navigators.FragmentNavigator;

public abstract class BaseFragment<V extends ViewDataBinding> extends Fragment {


    protected V binding;
    @Inject
    protected ViewModelProvider.Factory mFactory;

    @Inject
    protected FragmentNavigator mNavigator;
    @Inject
    protected FragmentManager mChildFragmentManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false);
        mNavigator.toString();
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    protected abstract int layoutId();
}
