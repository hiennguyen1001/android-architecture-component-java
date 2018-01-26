package hiennguyen.me.architecture.example.features.products;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.drextended.actionhandler.ActionHandler;
import com.drextended.actionhandler.action.BaseAction;
import com.drextended.actionhandler.listener.ActionClickListener;

import java.util.ArrayList;
import java.util.List;

import hiennguyen.me.architecture.BR;
import hiennguyen.me.architecture.R;
import hiennguyen.me.architecture.databinding.FragmentProductsBinding;
import hiennguyen.me.architecture.example.data.models.ActionType;
import hiennguyen.me.architecture.example.data.models.Product;
import hiennguyen.me.architecture.example.features.base.views.BaseFragment;
import hiennguyen.me.architecture.example.features.bindingmodels.ProductsBindingViewModel;
import hiennguyen.me.architecture.example.features.products.viewmodels.ProductsViewModel;
import hiennguyen.me.architecture.rvbindinglib.adapter.BindableAdapter;
import hiennguyen.me.architecture.rvbindinglib.delegate.ModelActionItemDelegate;

@SuppressWarnings("unchecked")
public class ProductsFragment extends BaseFragment<FragmentProductsBinding> {

    private static final String TAG = ProductsFragment.class.getSimpleName();

    private OnProductsFragmentInteractionListener listener;


    private BindableAdapter<List<ProductsBindingViewModel>, ProductsBindingViewModel> adapter;

    private ProductsViewModel viewModel;

    public static ProductsFragment newInstance() {
            ProductsFragment fragment = new ProductsFragment();
            Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //todo handle argunments
        }
    }


    private void initializeProductList() {
        final ActionClickListener actionHandler = new ActionHandler.Builder()
                .addAction(ActionType.OPEN_URL, new BaseAction<ProductsBindingViewModel>() {
                    @Override
                    public boolean isModelAccepted(Object model) {
                        return model instanceof ProductsBindingViewModel;
                    }

                    @Override
                    public void onFireAction(Context context, @Nullable View view,
                                             @Nullable String actionType,
                                             @Nullable ProductsBindingViewModel model) {
                        Toast.makeText(context, "product clicked: "+model.getDescription(), Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        adapter = new BindableAdapter<>(DIFF_CALLBACK, false,
                new ModelActionItemDelegate<>(actionHandler, ProductsBindingViewModel.class, R.layout.item_product, BR.product));

        binding.list.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        binding.list.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeProductList();
        viewModel = ViewModelProviders.of(this, mFactory).get(ProductsViewModel.class);
        loadProducts("Coffee");

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (listener != null) {
            listener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnProductsFragmentInteractionListener) {
            listener = (OnProductsFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnProductsFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_products;
    }

    DiffCallback<ProductsBindingViewModel> DIFF_CALLBACK = new DiffCallback<ProductsBindingViewModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull ProductsBindingViewModel oldItem, @NonNull ProductsBindingViewModel newItem) {
            //Todo implement later
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ProductsBindingViewModel oldItem, @NonNull ProductsBindingViewModel newItem) {
            //Todo implement later
            return false;
        }
    };

    private void loadProducts(String type) {
        viewModel.initialize(type);
        viewModel.loadProducts().observe(this, products -> {
            List<ProductsBindingViewModel> viewModels = new ArrayList<>();
            for (Product product : products) {
                viewModels.add(new ProductsBindingViewModel(product));
            }
            adapter.setItems(viewModels);
        });
    }

    public interface OnProductsFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
