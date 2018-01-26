package hiennguyen.me.architecture.example.features.bindingmodels;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;

import hiennguyen.me.architecture.example.data.models.CartItem;
import hiennguyen.me.architecture.example.data.models.Model;
import hiennguyen.me.architecture.example.data.models.Product;
import hiennguyen.me.architecture.example.utils.Utils;


public class CartItemBindingViewModel implements Model {

    private Product product;

    private int quantity;

    public CartItemBindingViewModel(CartItem item) {
        this.product = item.getProduct();
        this.quantity = item.getQuantity();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return String.format(Utils.getLocale(), "%1$,.2f", product.getPrice() * quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof CartItemBindingViewModel)) return false;

        CartItemBindingViewModel that = (CartItemBindingViewModel) o;

        if (quantity != that.quantity) return false;
        return product != null ? product.equals(that.product) : that.product == null;
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public boolean areItemsThemSameWith(Model model) {
        return false;
    }

    @Override
    public boolean areContentsThemSameWith(Model model) {
        return false;
    }

    public static class CallBack extends DiffCallback<CartItemBindingViewModel> {

        @Override
        public boolean areItemsTheSame(@NonNull CartItemBindingViewModel oldItem, @NonNull CartItemBindingViewModel newItem) {
            return oldItem.getProduct().getId() == newItem.getProduct().getId();

        }

        @Override
        public boolean areContentsTheSame(@NonNull CartItemBindingViewModel oldItem, @NonNull CartItemBindingViewModel newItem) {
            return oldItem.equals(newItem);
        }
    }
}
