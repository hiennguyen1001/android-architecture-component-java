package hiennguyen.me.architecture.example.data.repositories.remote;

import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.List;

import hiennguyen.me.architecture.example.data.models.Product;
import hiennguyen.me.architecture.example.data.models.realm.CategoriesRes;
import hiennguyen.me.architecture.example.data.services.NetworkingHelper;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.GET;


public class ProductRepository extends RemoteRepositories<ProductRepository.ProductSevice> {

    public ProductRepository(NetworkingHelper networkingHelper) {
        super(ProductSevice.class, networkingHelper);
    }

    public Single<List<CategoriesRes.Category>> getCategoryList() {
        return mRestApi.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(observerSchedule)
                .map(categoriesRes -> {
                    if(categoriesRes != null) {
                        return categoriesRes.categories;
                    } else {
                        return Collections.emptyList();
                    }
                });
    }

    public Single<List<Product>> getProducts(){
        return mRestApi.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(observerSchedule);
    }


    @Override
    protected GsonBuilder getCustomGsonBuilder() {
        return super.getCustomGsonBuilder();
    }

    public interface ProductSevice {

        @GET("5a16a9523100002b1e8d334f")
        Single<CategoriesRes> getCategories();

        @GET("5a16acc23100008a1e8d3365")
        Single<List<Product>> getProducts();
    }
}
