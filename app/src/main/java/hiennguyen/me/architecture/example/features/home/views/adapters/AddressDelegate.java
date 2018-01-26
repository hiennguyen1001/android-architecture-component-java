package hiennguyen.me.architecture.example.features.home.views.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.drextended.actionhandler.listener.ActionClickListener;

import java.util.List;

import hiennguyen.me.architecture.R;
import hiennguyen.me.architecture.databinding.ItemAddressBinding;
import hiennguyen.me.architecture.example.data.models.Model;
import hiennguyen.me.architecture.example.data.models.realm.RAddress;
import hiennguyen.me.architecture.rvbindinglib.adapter.BindingHolder;
import hiennguyen.me.architecture.rvbindinglib.delegate.ActionAdapterDelegate;


public class AddressDelegate extends ActionAdapterDelegate<Model, ItemAddressBinding> {
    public AddressDelegate(ActionClickListener actionHandler) {
        super(actionHandler);
    }

    @Override
    public boolean isForViewType(@NonNull List<Model> items, int position) {
        return items.get(position) instanceof RAddress;
    }

    @NonNull
    @Override
    public BindingHolder<ItemAddressBinding> onCreateViewHolder(ViewGroup parent) {
        return BindingHolder.newInstance(R.layout.item_address, LayoutInflater.from(parent.getContext()), parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull List<Model> items, int position, @NonNull BindingHolder<ItemAddressBinding> holder) {
        final RAddress address = (RAddress) items.get(position);
        holder.getBinding().setAddress(address);
        holder.getBinding().setActionHandler(getActionHandler());
    }
}
