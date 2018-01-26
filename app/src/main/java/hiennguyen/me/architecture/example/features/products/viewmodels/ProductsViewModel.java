package hiennguyen.me.architecture.example.features.products.viewmodels;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;


import java.util.List;

import javax.inject.Inject;

import hiennguyen.me.architecture.example.data.models.Product;
import hiennguyen.me.architecture.example.data.models.realm.CategoriesRes;
import hiennguyen.me.architecture.example.data.repositories.remote.ProductRepository;
import hiennguyen.me.architecture.example.features.base.viewmodels.BaseViewModel;

public class ProductsViewModel extends BaseViewModel {

    private String type;
    private ProductRepository productRepository;
    MutableLiveData<List<CategoriesRes.Category>> categoriesLive = new MutableLiveData<>();


    @Inject
    public ProductsViewModel(Application application, ProductRepository productRepository) {
        super(application);
        this.productRepository = productRepository;
    }

    public void initialize(String type){
        this.type = type;
    }

    public LiveData<List<Product>> loadProducts(){
        if(this.type == null){
            throw new IllegalStateException("initialize() method has to be called before loadProducts()");
        }
        MutableLiveData<List<Product>> products = new MutableLiveData<>();
        this.productRepository.getProducts().subscribe(products1 -> products.setValue(products1)
                , throwable -> throwable.printStackTrace());
        return products;
    }

    public void loadCategory() {
        productRepository.getCategoryList().subscribe(categories -> {
            categoriesLive.setValue(categories);
        }, throwable -> {
            throwable.printStackTrace();
        });
    }

    public LiveData<List<CategoriesRes.Category>> loadCategories(){
        loadCategory();
        return categoriesLive;
    }

}
