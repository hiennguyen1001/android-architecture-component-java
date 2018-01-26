package hiennguyen.me.architecture.example.features.cart.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.drextended.actionhandler.ActionHandler;
import com.drextended.actionhandler.action.BaseAction;
import com.drextended.actionhandler.listener.ActionClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import hiennguyen.me.architecture.R;
import hiennguyen.me.architecture.databinding.FragmentCartBinding;
import hiennguyen.me.architecture.example.data.models.ActionType;
import hiennguyen.me.architecture.example.data.models.CartItem;
import hiennguyen.me.architecture.example.data.models.PromotionCode;
import hiennguyen.me.architecture.example.data.models.Resource;
import hiennguyen.me.architecture.example.features.base.views.BaseFragment;
import hiennguyen.me.architecture.example.features.bindingmodels.CartItemBindingViewModel;
import hiennguyen.me.architecture.example.features.cart.viewmodels.CartViewModel;
import hiennguyen.me.architecture.rvbindinglib.ListConfig;
import hiennguyen.me.architecture.rvbindinglib.adapter.BindableAdapter;
import hiennguyen.me.architecture.rvbindinglib.delegate.ModelActionItemDelegate;


public class CartFragment extends BaseFragment<FragmentCartBinding> {

    private static final String TAG = CartFragment.class.getSimpleName();

    private static final double VAT = 0.1;


    private CartViewModel viewModel;

    private BindableAdapter<List<CartItemBindingViewModel>, CartItemBindingViewModel> adapter;

    @Override
    protected int layoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this, mFactory).get(CartViewModel.class);
        initCartList();
        loadCartItems();
        loadPromotionCode();
        setUpPromoteCodeHandler();
    }

    private void loadPromotionCode() {
        viewModel.getPromotionCode().observe(this, promoCodeResource -> {
            if (promoCodeResource != null) {
                binding.setPromoCode(promoCodeResource);
                updatePrice();
            }
        });
    }

    private void setUpPromoteCodeHandler() {
        binding.etPromoteCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    showApplyPromoteCodeButton();
                } else {
                    hideApplyPromoteCodeButton();
                }

            }
        });
        binding.ivClearPromoCode.setOnClickListener(view -> {
            binding.etPromoteCode.setText("");
            viewModel.setPromoCode("");
        });
        binding.btnApplyPromoteCode.setOnClickListener(view
                -> {
            viewModel.setPromoCode(binding.etPromoteCode.getText().toString());
            binding.btnApplyPromoteCode.setVisibility(View.GONE);
        });
    }

    private void hideApplyPromoteCodeButton() {
        binding.btnApplyPromoteCode.setVisibility(View.INVISIBLE);
        binding.ivClearPromoCode.setVisibility(View.INVISIBLE);
    }

    private void showApplyPromoteCodeButton() {
        binding.btnApplyPromoteCode.setVisibility(View.VISIBLE);
        binding.ivClearPromoCode.setVisibility(View.VISIBLE);
    }

    private void initCartList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvCart.setLayoutManager(layoutManager);
        final ActionClickListener cartHandler = new ActionHandler.Builder()
                .addAction(ActionType.OPEN_URL, new BaseAction<CartItemBindingViewModel>() {
                    @Override
                    public boolean isModelAccepted(Object model) {
                        return model instanceof CartItemBindingViewModel;
                    }

                    @Override
                    public void onFireAction(Context context, @Nullable View view,
                                             @Nullable String actionType,
                                             @Nullable CartItemBindingViewModel model) {
                    }
                })
                .addAction(ActionType.NUMBER_PICKER_QUANTITY_CHANGE, new BaseAction<CartItemBindingViewModel>() {
                    @Override
                    public boolean isModelAccepted(Object model) {
                        return model instanceof CartItemBindingViewModel;
                    }

                    @Override
                    public void onFireAction(Context context, @Nullable View view, @Nullable String actionType, @Nullable CartItemBindingViewModel model) {
                        viewModel.updateCartItemQuantity(model.getProduct().getId(), model.getQuantity());
                    }
                })
                .build();


        //noinspection unchecked
        adapter = new BindableAdapter<>(new CartItemBindingViewModel.CallBack(), false,
                new ModelActionItemDelegate<>(cartHandler, CartItemBindingViewModel.class, R.layout.item_cart, BR.cart));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        ListConfig listConfig = new ListConfig.Builder(adapter)
                .setDefaultDividerEnabled(true)
                .addItemDecoration(dividerItemDecoration)
                .build(getContext());
        listConfig.applyConfig(getContext(), binding.rvCart);
    }

    private void loadCartItems() {
        viewModel.getCartItemsResource().observe(this, cartItemsResource -> {
            binding.setCartItems(cartItemsResource);
            if (cartItemsResource != null && cartItemsResource.data != null) {
                List<CartItemBindingViewModel> items = new ArrayList<>();
                for (CartItem cartItem :
                        cartItemsResource.data) {
                    if (cartItem.getQuantity() > 0) {
                        items.add(new CartItemBindingViewModel(cartItem));
                    }
                }
                adapter.setItems(items);
                updatePrice();
                showEmptyLayout(items.isEmpty());
            }
        });
    }

    private void updatePrice() {
        Resource<List<CartItem>> cartItemsResource = viewModel.getCartItemsResource().getValue();
        if (cartItemsResource != null && cartItemsResource.data != null) {
            double subPrice = 0, vatPrice, totalPrice, discount = 0, discountRate = 0;
            for (CartItem cartItem :
                    cartItemsResource.data) {
                if (cartItem.getQuantity() > 0) {
                    subPrice += cartItem.getQuantity() * cartItem.getProduct().getPrice();
                }
            }
            if (viewModel.getPromotionCode().getValue() != null) {
                PromotionCode promoCode = viewModel.getPromotionCode().getValue().data;
                discountRate = promoCode != null ? promoCode.getDiscountRate() : 0;
            }
            vatPrice = VAT * subPrice;
            discount = (vatPrice + subPrice) * discountRate;
            totalPrice = vatPrice + subPrice - discount;
            binding.tvSubtotalPrice
                    .setText(String.format(Locale.getDefault(), "%1$,.2f", subPrice));
            binding.tvVatPrice
                    .setText(String.format(Locale.getDefault(), "%1$,.2f", vatPrice));
            binding.tvDiscountPrice
                    .setText(String.format(Locale.getDefault(), "-%1$,.2f", discount));
            binding.tvTotalPrice
                    .setText(String.format(Locale.getDefault(), "%1$,.2f", totalPrice));
        }
    }

    private void showEmptyLayout(boolean show) {
        binding.emptyLayout.getRoot().setVisibility(show ? View.VISIBLE : View.GONE);
    }


}
