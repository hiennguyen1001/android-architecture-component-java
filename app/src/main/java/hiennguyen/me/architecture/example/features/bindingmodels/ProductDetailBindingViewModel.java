package hiennguyen.me.architecture.example.features.bindingmodels;


import java.util.List;

import hiennguyen.me.architecture.R;
import hiennguyen.me.architecture.example.data.models.Product;

public class ProductDetailBindingViewModel {

    private String name;

    private List<String> imageUrls;

    private String description;

    private double price;

    private boolean outOfStock;

    public ProductDetailBindingViewModel(Product product) {
        name = product.getName();
        imageUrls = product.getImageUrls();
        description = product.getDescription();
        price = product.getPrice();
        outOfStock = product.isOutOfStock();
    }

    public int getAddToCartButtonColor() {
        return outOfStock ? R.color.gray : R.color.colorPrimary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(boolean outOfStock) {
        this.outOfStock = outOfStock;
    }
}
