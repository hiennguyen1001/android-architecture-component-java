package hiennguyen.me.architecture.example.features.products.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.drextended.actionhandler.listener.ActionClickListener;

import java.util.List;

import hiennguyen.me.architecture.R;
import hiennguyen.me.architecture.databinding.ItemProductBinding;
import hiennguyen.me.architecture.example.features.bindingmodels.ProductsBindingViewModel;
import hiennguyen.me.architecture.rvbindinglib.adapter.BindingHolder;
import hiennguyen.me.architecture.rvbindinglib.delegate.ActionAdapterDelegate;

public class ProductsDelegate extends ActionAdapterDelegate<ProductsBindingViewModel, ItemProductBinding> {

    public ProductsDelegate(ActionClickListener actionHandler) {
        super(actionHandler);
    }

    @NonNull
    @Override
    public BindingHolder<ItemProductBinding> onCreateViewHolder(ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return BindingHolder
                .newInstance(R.layout.item_product, layoutInflater, parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull List<ProductsBindingViewModel> items, int position,
                                 @NonNull BindingHolder<ItemProductBinding> holder) {
        holder.getBinding().setProduct(items.get(position));
    }

    @Override
    protected boolean isForViewType(@NonNull List<ProductsBindingViewModel> items, int position) {
        return items.get(position) instanceof ProductsBindingViewModel;
    }
}
