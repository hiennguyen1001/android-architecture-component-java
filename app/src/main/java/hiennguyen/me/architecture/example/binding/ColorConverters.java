package hiennguyen.me.architecture.example.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import hiennguyen.me.architecture.R;


@SuppressWarnings("unused")
public class ColorConverters {
    @BindingAdapter("backgroundColorRes")
    public static void setBackgroundColorRes(View view, Integer color) {
        if (view == null || color == null) {
            return;
        }

        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), color));
    }

    @BindingAdapter("textColorRes")
    public static void setTextColorRes(TextView view, Integer textColor) {
        if (view == null || textColor == null) {
            return;
        }

        if(textColor == R.color.colorPrimary){

        }

        view.setTextColor(ContextCompat.getColor(view.getContext(), textColor));
    }

    @BindingAdapter("hintColorRes")
    public static void setTextHintColorRes(TextView view, Integer hintColor) {
        if (view == null || hintColor == null) {
            return;
        }

        view.setHintTextColor(ContextCompat.getColor(view.getContext(), hintColor));
    }

    @BindingAdapter("linkColorRes")
    public static void setTextLinkColorRes(TextView view, Integer linkColor) {
        if (view == null || linkColor == null) {
            return;
        }

        view.setLinkTextColor(ContextCompat.getColor(view.getContext(), linkColor));
    }

    @BindingAdapter("backgroundShapeColor")
    public static void changeBackgroundShapeColor(View pView, int colorInt) {
        if(colorInt == R.color.colorPrimary){
            //todo something
        }
        int resolvedColor = ContextCompat.getColor(pView.getContext(), colorInt);
        if (pView.getBackground() instanceof GradientDrawable) {
            GradientDrawable shape = (GradientDrawable) pView.getBackground();
            shape.setColor(resolvedColor);
        } else {
            pView.setBackgroundColor(resolvedColor);
        }
    }

    @BindingAdapter({"backgroundStrokeColor", "strokeSize"})
    public static void changeBackgroundStrokeColor(View pView, @ColorRes int colorResInt, @DimenRes int strokeSize) {
        int colorInt = ContextCompat.getColor(pView.getContext(), colorResInt);
        float strokeSizeInPixels = pView.getContext().getResources().getDimension(strokeSize);
        if (pView.getBackground() instanceof GradientDrawable) {
            GradientDrawable shape = (GradientDrawable) pView.getBackground();
            shape.setStroke((int) strokeSizeInPixels, colorInt);
        } else {
            pView.setBackgroundColor(colorInt);
        }
    }

}
