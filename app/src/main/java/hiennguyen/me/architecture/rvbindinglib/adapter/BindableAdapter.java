/*
 *  Copyright Roman Donchenko. All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package hiennguyen.me.architecture.rvbindinglib.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.DiffCallback;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import java.util.List;

import hiennguyen.me.architecture.rvbindinglib.helper.ComputingAdapterChangedHelper;

/**
 * RecyclerView Adapter for using with data binding. Uses List of items as dataset.
 * Based on AdapterDelegates Library by Hannes Dorfmann https://github.com/sockeqwe/AdapterDelegates
 *
 * @param <T> The type of the datasoure / items
 */

public class BindableAdapter<H extends List<T>, T> extends BaseBindableAdapter<H> {

    private final ComputingAdapterChangedHelper<T> mHelper;


    public BindableAdapter(@NonNull DiffCallback<T> diffCallback, boolean changeOnMainThread) {
        mHelper = new ComputingAdapterChangedHelper<>(this, diffCallback, changeOnMainThread);
    }

    public BindableAdapter(@NonNull DiffCallback<T> diffCallback, boolean changeOnMainThread, @NonNull final AdapterDelegatesManager<H>
            delegatesManager) {
        super(delegatesManager);
        mHelper = new ComputingAdapterChangedHelper<>(this, diffCallback, changeOnMainThread);
    }

    public BindableAdapter(@NonNull DiffCallback<T> diffCallback, boolean changeOnMainThread, AdapterDelegate<H>... adapterDelegates) {
        super(adapterDelegates);
        mHelper = new ComputingAdapterChangedHelper<>(this, diffCallback, changeOnMainThread);
    }

    public BindableAdapter(@NonNull DiffCallback<T> diffCallback, boolean changeOnMainThread, final H items,
                           final AdapterDelegatesManager<H> delegatesManager) {
        super(items, delegatesManager);
        mHelper = new ComputingAdapterChangedHelper<>(this, diffCallback, changeOnMainThread);
        mHelper.setList(items);
    }

    public BindableAdapter(@NonNull DiffCallback<T> diffCallback, boolean changeOnMainThread, final H items,
                           AdapterDelegate<H>... adapterDelegates) {
        super(items, adapterDelegates);
        mHelper = new ComputingAdapterChangedHelper<>(this, diffCallback, changeOnMainThread);
        mHelper.setList(items);
    }

    @Override
    public int getItemCount() {
        return mHelper.getItemCount();
    }

    @Override
    public H getItems() {
        return (H) mHelper.getList();
    }

    @Nullable
    protected T getItem(int position) {
        return mHelper.getItem(position);
    }


    @Override
    public void setItems(H items) {
        super.setItems(items);
        mHelper.setList(items);
    }

}
