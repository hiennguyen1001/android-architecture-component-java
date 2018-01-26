package hiennguyen.me.architecture.example.features.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hiennguyen.me.architecture.R;
import hiennguyen.me.architecture.example.binding.ColorConverters;


public class NumberPicker extends LinearLayout {

    private TextView quantityTextView;

    private OnQuantityChangeListener quantityChangeListener;

    private int currentQuantity = 0;

    private String textHolder;

    public NumberPicker(Context context) {
        super(context);
        initializeViews(context);
    }

    public NumberPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.NumberPicker,
                0, 0);

        try {
            currentQuantity = a.getInteger(R.styleable.NumberPicker_currentQuantity, 0);
            textHolder = a.getString(R.styleable.NumberPicker_textHolder);
            if (textHolder == null) {
                textHolder = context.getString(R.string.number_picker_default_text_holder);
            }
        } finally {
            a.recycle();
        }
        initializeViews(context);
    }

    public NumberPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ImageView minusTextView = findViewById(R.id.tv_minus);
        ImageView plusTextView = findViewById(R.id.tv_plus);
        quantityTextView = findViewById(R.id.tv_quantity);
        if (currentQuantity == 0) {
            quantityTextView.setText(textHolder);
        } else {
            quantityTextView.setText(String.valueOf(currentQuantity));
        }

        ColorConverters.setTextColorRes(quantityTextView, R.color.black);

        minusTextView.setOnClickListener(view -> {
            if (currentQuantity > 0) {
                currentQuantity--;
            }
            notifyQuantityChanged(currentQuantity);
            if (currentQuantity == 0) {
                quantityTextView.setText(textHolder);
            } else {
                quantityTextView.setText(String.valueOf(currentQuantity));
            }
        });

        plusTextView.setOnClickListener(view -> {
            currentQuantity++;
            notifyQuantityChanged(currentQuantity);
            quantityTextView.setText(String.valueOf(currentQuantity));
        });
    }


    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
        if (currentQuantity == 0) {
            quantityTextView.setText(textHolder);
        } else {
            quantityTextView.setText(String.valueOf(currentQuantity));
        }
    }

    public interface OnQuantityChangeListener {
        void onChange(int quantity);
    }

    public void notifyQuantityChanged(int quantity) {
        if (quantityChangeListener != null) {
            quantityChangeListener.onChange(quantity);
        }
        invalidate();
        requestLayout();
    }

    public void setQuantityChangeListener(OnQuantityChangeListener listener) {
        this.quantityChangeListener = listener;
    }

    public void removeQuantityChangeListener() {
        this.quantityChangeListener = null;
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.partial_number_picker, this);
    }

}
