package hiennguyen.me.architecture.example.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product implements Model {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("price")
    private double price;

    @SerializedName("imageUrls")
    private List<String> imageUrls;

    private boolean outOfStock;

    public Product() {
    }


    public Product(int id, String name, String description, double price, List<String> imageUrls, boolean outOfStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrls = imageUrls;
        this.outOfStock = outOfStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public boolean isOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(boolean outOfStock) {
        this.outOfStock = outOfStock;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @Override
    public boolean areItemsThemSameWith(Model model) {
        if(model instanceof Product) {
            return model == this;
        }
        return false;
    }

    @Override
    public boolean areContentsThemSameWith(Model model) {
        if (model instanceof Product) {
            return ((Product) model).name.equals(name) && ((Product) model).description.equals(description);
        }
        return false;
    }
      
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (Double.compare(product.price, price) != 0) return false;
        if (outOfStock != product.outOfStock) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null)
            return false;
        return imageUrls != null ? imageUrls.equals(product.imageUrls) : product.imageUrls == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (imageUrls != null ? imageUrls.hashCode() : 0);
        result = 31 * result + (outOfStock ? 1 : 0);
        return result;
    }
}
