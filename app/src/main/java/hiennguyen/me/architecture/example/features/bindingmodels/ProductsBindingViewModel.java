package hiennguyen.me.architecture.example.features.bindingmodels;


import java.util.List;

import hiennguyen.me.architecture.example.data.models.Product;

public class ProductsBindingViewModel {

    private List<String> imageUrls;

    private String description;

    private double price;

    public ProductsBindingViewModel(Product product) {
        imageUrls = product.getImageUrls();
        description = product.getDescription();
        price = product.getPrice();
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
}