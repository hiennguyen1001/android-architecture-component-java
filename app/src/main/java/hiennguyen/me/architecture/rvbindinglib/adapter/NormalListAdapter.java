package hiennguyen.me.architecture.rvbindinglib.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.recyclerview.extensions.ListAdapterConfig;
import android.support.v7.widget.RecyclerView;


import java.util.List;

import hiennguyen.me.architecture.rvbindinglib.helper.ComputingAdapterChangedHelper;

/**
 * This class is modified from {@link android.arch.paging.PagedListAdapter} for presenting data list from {@link List}
 * in {@link RecyclerView}
 * <p>
 * This class is a convenience wrapper around {@link ComputingAdapterChangedHelper} that implements common default
 * behavior for item getting, and listening to List update callbacks in main or background thread.
 * <p>
 *
 * @param <T> Type of the List this helper will receive.
 * @param <VH> A class that extends ViewHolder that will be used by the adapter.
 */
public abstract class NormalListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>  {

    private final ComputingAdapterChangedHelper<T> mHelper;


    /**
     * Creates a PagedListAdapter with default threading and
     * {@link android.support.v7.util.ListUpdateCallback}.
     *
     * Convenience for {@link #NormalListAdapter(ListAdapterConfig)}, which uses default threading
     * behavior.
     *
     * @param diffCallback The {@link DiffCallback} instance to compare items in the list.
     */
    protected NormalListAdapter(@NonNull DiffCallback<T> diffCallback) {
        mHelper = new ComputingAdapterChangedHelper<>(this, diffCallback, true);
    }

    protected NormalListAdapter(@NonNull DiffCallback<T> diffCallback, boolean changeOnMainThread) {
        mHelper = new ComputingAdapterChangedHelper<>(this, diffCallback, changeOnMainThread);
    }

    @SuppressWarnings("unused, WeakerAccess")
    protected NormalListAdapter(@NonNull ListAdapterConfig<T> config) {
        mHelper = new ComputingAdapterChangedHelper<>(new ComputingAdapterChangedHelper.AdapterCallback(this), config);
    }

    /**
     * Set the new list to be displayed.
     * <p>
     * If a list is already being displayed, a diff will be computed on a background thread, which
     * will dispatch Adapter.notifyItem events on the main thread.
     *
     * @param list The new list to be displayed.
     */
    public void setList(List<T> list) {
        mHelper.setList(list);
    }

    @Nullable
    protected T getItem(int position) {
        return mHelper.getItem(position);
    }

    @Override
    public int getItemCount() {
        return mHelper.getItemCount();
    }

    /**
     * Returns the list currently being displayed by the Adapter.
     * <p>
     * This is not necessarily the most recent list passed to {@link #setList(List)}, because a
     * diff is computed asynchronously between the new list and the current list before updating the
     * currentList value.
     *
     * @return The list currently being displayed.
     */
    @Nullable
    public List<T> getList() {
        return mHelper.getList();
    }
}
