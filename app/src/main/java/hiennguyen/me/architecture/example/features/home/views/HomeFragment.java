package hiennguyen.me.architecture.example.features.home.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.view.View;
import android.widget.Toast;

import com.drextended.actionhandler.ActionHandler;
import com.drextended.actionhandler.action.BaseAction;
import com.drextended.actionhandler.listener.ActionClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import hiennguyen.me.architecture.R;
import hiennguyen.me.architecture.BR;
import hiennguyen.me.architecture.databinding.FragmentHomeBinding;
import hiennguyen.me.architecture.example.data.models.ActionType;
import hiennguyen.me.architecture.example.data.models.Ad;
import hiennguyen.me.architecture.example.data.models.Model;
import hiennguyen.me.architecture.example.data.models.realm.RAddress;
import hiennguyen.me.architecture.example.features.base.views.BaseFragment;
import hiennguyen.me.architecture.example.features.home.viewmodels.HomeViewModel;
import hiennguyen.me.architecture.rvbindinglib.ListConfig;
import hiennguyen.me.architecture.rvbindinglib.adapter.BindableAdapter;
import hiennguyen.me.architecture.rvbindinglib.delegate.ModelActionItemDelegate;


public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private BindableAdapter<List<Model>, Model> adapter;


    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HomeViewModel viewModel = ViewModelProviders.of(this, mFactory).get(HomeViewModel.class);
        binding.setViewModel(viewModel);
        final ActionClickListener actionHandler = new ActionHandler.Builder()
                .addAction(ActionType.OPEN_URL, new BaseAction<Ad>() {
                    @Override
                    public boolean isModelAccepted(Object model) {
                        return model instanceof Ad;
                    }

                    @Override
                    public void onFireAction(Context context, @Nullable View view, @Nullable String actionType, @Nullable Ad model) {
                        Toast.makeText(context, String.format(Locale.US, "Click on ad %s with url %s", model.getName(), model.getBanner()),
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        //noinspection unchecked
        adapter = new BindableAdapter<>(DIFF_CALLBACK, false,
                new ModelActionItemDelegate<>(actionHandler, RAddress.class, R.layout.item_address, BR.address),
                new ModelActionItemDelegate<>(actionHandler, Ad.class, R.layout.item_ad, BR.ad)
        );

        ListConfig listConfig = new ListConfig.Builder(adapter)
                .setDefaultDividerEnabled(true)
                .build(getContext());
        listConfig.applyConfig(getContext(), binding.rvTodo);

        viewModel.getAddresses().observe(this, todoList -> {
            List<Model> items = new ArrayList<>();
            items.addAll(todoList);

            Ad ad = new Ad();
            ad.setName("ad " + todoList.size());
            ad.setBanner("http://techli.com/wp-content/uploads/2012/05/advertising-banner.jpg");

            items.add(todoList.size()/ 2, ad);

            adapter.setItems(items);
        });

    }

    DiffCallback<Model> DIFF_CALLBACK = new DiffCallback<Model>() {
        @Override
        public boolean areItemsTheSame(@NonNull Model oldItem, @NonNull Model newItem) {
            return oldItem.areItemsThemSameWith(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Model oldItem, @NonNull Model newItem) {
            return oldItem.areContentsThemSameWith(newItem);
        }
    };
}
