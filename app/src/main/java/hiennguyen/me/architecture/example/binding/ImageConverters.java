package hiennguyen.me.architecture.example.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import hiennguyen.me.architecture.example.utils.GlideApp;


@SuppressWarnings("unused")
public class ImageConverters {
    @BindingAdapter("backgroundRes")
    public static void setBackgroundDrawableRes(View view, Integer resId) {
        if (view == null || resId == null) {
            return;
        }

        view.setBackgroundResource(resId);
    }

    @BindingAdapter("imageRes")
    public static void setImageDrawableRes(ImageView view, Integer resId) {
        if (view == null || resId == null) {
            return;
        }

        view.setImageResource(resId);
    }

    @BindingAdapter(value = {"imageUrl", "placeholder"}, requireAll = false)
    public static void setImageRemote(ImageView view, String url, Drawable placeholder) {
        if (view == null || url == null) {
            return;
        }

        if (placeholder != null) {
            GlideApp.with(view)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(placeholder)
                    .into(view);
        } else {
            GlideApp.with(view.getContext()).load(url).into(view);
        }
    }
}
