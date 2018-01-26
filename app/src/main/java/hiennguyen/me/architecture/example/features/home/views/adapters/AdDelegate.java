package hiennguyen.me.architecture.example.features.home.views.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.drextended.actionhandler.listener.ActionClickListener;

import java.util.List;

import hiennguyen.me.architecture.R;
import hiennguyen.me.architecture.databinding.ItemAdBinding;
import hiennguyen.me.architecture.example.data.models.Ad;
import hiennguyen.me.architecture.example.data.models.Model;
import hiennguyen.me.architecture.rvbindinglib.adapter.BindingHolder;
import hiennguyen.me.architecture.rvbindinglib.delegate.ActionAdapterDelegate;

public class AdDelegate extends ActionAdapterDelegate<Model, ItemAdBinding> {
    public AdDelegate(ActionClickListener actionHandler) {
        super(actionHandler);
    }

    @Override
    public boolean isForViewType(@NonNull List<Model> items, int position) {
        return items.get(position) instanceof Ad;
    }

    @NonNull
    @Override
    public BindingHolder<ItemAdBinding> onCreateViewHolder(ViewGroup parent) {
        return BindingHolder.newInstance(R.layout.item_ad, LayoutInflater.from(parent.getContext()), parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull List<Model> items, int position, @NonNull BindingHolder<ItemAdBinding> holder) {
        final Ad ad = (Ad) items.get(position);
        holder.getBinding().setAd(ad);
        holder.getBinding().setActionHandler(getActionHandler());
    }
}