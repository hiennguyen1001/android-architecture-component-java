package hiennguyen.me.architecture.example.binding;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import hiennguyen.me.architecture.example.utils.FontCache;


@SuppressWarnings("unused")
public class TextConverters {
    @BindingAdapter("font")
    public static void setFont(TextView textView, String fontName) {
        if (textView == null || fontName == null) {
            return;
        }

        textView.setTypeface(FontCache.getInstance(textView.getContext()).get(fontName));
    }

    @BindingAdapter("text")
    public static void setText(TextView textView, String text) {
        if (textView == null || text == null) {
            return;
        }

        textView.setText(text);
    }
}
