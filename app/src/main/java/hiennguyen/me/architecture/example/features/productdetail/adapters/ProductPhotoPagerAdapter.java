package hiennguyen.me.architecture.example.features.productdetail.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import hiennguyen.me.architecture.R;

public class ProductPhotoPagerAdapter extends PagerAdapter {

    private List<String> imageUrls;

    private Context context;

    public ProductPhotoPagerAdapter(Context c, List<String> imageUrls) {
        context = c;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.item_product_image_sideshow, container, false);
        ImageView productImageView = view.findViewById(R.id.product_image);
        Glide.with(context).load(imageUrls.get(position)).into(productImageView);
        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        notifyDataSetChanged();
    }
}
