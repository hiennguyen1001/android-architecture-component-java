package hiennguyen.me.architecture.example.features.productdetail.viewmodels;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hiennguyen.me.architecture.example.data.models.Product;
import hiennguyen.me.architecture.example.features.base.viewmodels.BaseViewModel;

public class ProductDetailViewModel extends BaseViewModel {

    private MutableLiveData<Product> product;

    @Inject
    public ProductDetailViewModel(Application application) {
        super(application);
    }

    public LiveData<Product> loadProduct(int id){
        product = new MutableLiveData<>();
        List<String> photos = new ArrayList<>();
        photos.add("https://static.pexels.com/photos/2232/vegetables-italian-pizza-restaurant.jpg");
        photos.add("https://img.huffingtonpost.com/asset/585be1aa1600002400bdf2a6.jpeg?ops=scalefit_970_noupscale");
        photos.add("http://givefoodgetfood.ca/wp-content/uploads/2015/04/Good-Food-Guide-editor-calls-for-more-veggie-options-on-pub-menus.jpg");
        product.setValue(new Product(id, "Apple"
                , "this is an apple,this is an apple, this is an apple,this is an apple, this is an apple,this is an apple, this is an apple,this is an apple, this is an apple,this is an apple, this is an apple,this is an apple, this is an apple,this is an apple, this is an apple,this is an apple, this is an apple,this is an apple, this is an apple,this is an apple, this is an apple,this is an apple, this is an apple,this is an apple, this is an apple,this is an apple, this is an apple,this is an apple, this is an apple,this is an apple, this is an apple,this is an apple, "
                , 11.3, photos
                , false));
        return product;
    }

}
