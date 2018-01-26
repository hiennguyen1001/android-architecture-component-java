package hiennguyen.me.architecture.example.features.cart.viewmodels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hiennguyen.me.architecture.R;
import hiennguyen.me.architecture.example.data.models.CartItem;
import hiennguyen.me.architecture.example.data.models.Product;
import hiennguyen.me.architecture.example.data.models.PromotionCode;
import hiennguyen.me.architecture.example.data.models.Resource;
import hiennguyen.me.architecture.example.data.repositories.Repository;
import hiennguyen.me.architecture.example.data.repositories.SimpleRepository;
import hiennguyen.me.architecture.example.features.base.viewmodels.BaseViewModel;
import hiennguyen.me.architecture.example.utils.AbsentLiveData;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CartViewModel extends BaseViewModel {

    private static final String TAG = CartViewModel.class.getSimpleName();

    private final MutableLiveData<Resource<List<CartItem>>> cartItemsResource = new MutableLiveData<>();

    private MutableLiveData<String> userInputPromotionCode = new MutableLiveData<>();

    private LiveData<Resource<PromotionCode>> promotionCode;

    private Repository<Product> productRepository;

    @Inject
    public CartViewModel(Application application) {
        super(application);
        productRepository = new SimpleRepository<>();
        userInputPromotionCode.setValue("");
        promotionCode = Transformations.switchMap(userInputPromotionCode, input -> {
            if (input == null || input.trim().isEmpty()) {
                return AbsentLiveData.create();
            } else {
                return validatePromotionCode(input);
            }
        });
        Flowable.fromCallable(() -> {
            List<CartItem> cartItems = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                String description = getApplication().getString(R.string.lorem);
                List<String> photos = new ArrayList<>();
                photos.add("https://static.pexels.com/photos/2232/vegetables-italian-pizza-restaurant.jpg");
                photos.add("https://img.huffingtonpost.com/asset/585be1aa1600002400bdf2a6.jpeg?ops=scalefit_970_noupscale");
                photos.add("http://givefoodgetfood.ca/wp-content/uploads/2015/04/Good-Food-Guide-editor-calls-for-more-veggie-options-on-pub-menus.jpg");

                Product product = new Product(i, "Apple" + i, i + description, 11.3, photos, false);
                product.setOutOfStock(i % 2 == 0);
                CartItem cartItem = new CartItem(product, i);
                cartItems.add(cartItem);
            }
            return cartItems;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        cartItems -> {
                            cartItemsResource.setValue(Resource.success(cartItems));
                        }, throwable -> {
                        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        productRepository.tearDown();
    }

    public LiveData<Resource<PromotionCode>> getPromotionCode() {
        return promotionCode;
    }

    public void setPromoCode(@NonNull String promoCode) {
        userInputPromotionCode.setValue(promoCode.toLowerCase().trim());
    }

    @SuppressLint("StaticFieldLeak")
    public LiveData<Resource<List<CartItem>>> getCartItemsResource() {
        return this.cartItemsResource;
    }

    public void updateCartItemQuantity(int itemId, int quantity) {
        if (cartItemsResource.getValue() != null) {
            List<CartItem> cartItems = this.cartItemsResource.getValue().data;
            if (cartItems != null) {
                for (CartItem item : cartItems) {
                    if (item.getProduct().getId() == itemId) {
                        item.setQuantity(quantity);
                        this.cartItemsResource.setValue(Resource.success(cartItems));
                        return;
                    }
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private LiveData<Resource<PromotionCode>> validatePromotionCode(final String userInputCode) {
        MutableLiveData<Resource<PromotionCode>> promotionCodeResource = new MutableLiveData<>();
        promotionCodeResource.setValue(Resource.loading(null));
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    //simulate network loading
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                PromotionCode code;
                if (userInputCode.toLowerCase().equals("less15")) {
                    code = new PromotionCode(0.15, PromotionCode.Status.VALID, "less15");
                    promotionCodeResource.setValue(Resource.success(code));
                } else if (userInputCode.toLowerCase().equals("less20")) {
                    code = new PromotionCode(0.2, PromotionCode.Status.VALID, "less20");
                    promotionCodeResource.setValue(Resource.success(code));
                } else {
                    code = new PromotionCode(0, PromotionCode.Status.INVALID, null);
                    promotionCodeResource.setValue(Resource.success(code));
                }
                super.onPostExecute(aVoid);
            }
        }.execute();
        return promotionCodeResource;
    }


}
