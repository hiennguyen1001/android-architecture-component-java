package hiennguyen.me.architecture.example.binding;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.view.View;

import com.drextended.actionhandler.listener.ActionClickListener;

import hiennguyen.me.architecture.example.features.bindingmodels.CartItemBindingViewModel;
import hiennguyen.me.architecture.example.features.customviews.NumberPicker;

@SuppressWarnings("unused")
public class Converters {
    /**
     * Binding adapter to assign an action to a view using android data binding approach.
     * Sample:
     * <pre>
     * &lt;Button
     *     android:layout_width="wrap_content"
     *     android:layout_height="wrap_content"
     *
     *     android:actionHandler="@{someActionHandler}"
     *     android:actionType='@{"send_message"}'
     *     android:actionTypeLongClick='@{"show_menu"}'
     *     android:model="@{user}"
     *
     *     android:text="@string/my_button_text"/&gt;
     * </pre>
     *
     * @param view                The View to bind an action
     * @param actionHandler       The action handler which will handle an action
     * @param actionType          The action type, which will be handled on view clicked
     * @param actionTypeLongClick The action type, which will be handled on view long clicked
     * @param model               The model which will be handled
     */
    @BindingAdapter(
            value = {"actionHandler", "actionType", "actionTypeLongClick", "model", "modelLongClick"},
            requireAll = false
    )
    public static void setActionHandler(final View view, final ActionClickListener actionHandler, final String actionType, final String actionTypeLongClick, final Object model, final Object modelLongClick) {
        if (actionHandler != null) {
            if (actionType != null) {
                view.setOnClickListener(v -> actionHandler.onActionClick(view, actionType, model));
            }

            if (actionTypeLongClick != null) {
                view.setOnLongClickListener(v -> {
                    actionHandler.onActionClick(view, actionTypeLongClick, modelLongClick != null ? modelLongClick : model);
                    return true;
                });
            }
        }
    }

    @BindingAdapter(value = {"actionHandler", "actionTypeQuantityChange", "model"})
    public static void setActionHandlerQuantityChanged(final NumberPicker view, final ActionClickListener actionHandler, final String actionTypeQuantity, final Object model){
        if(actionHandler != null){
            if (actionTypeQuantity != null){
                view.setQuantityChangeListener(quantity -> {
                    ((CartItemBindingViewModel) model).setQuantity(quantity);
                    actionHandler.onActionClick(view, actionTypeQuantity, model);
                });
            }
        }
    }

    @BindingAdapter("visibleOrGone")
    public static void bindVisibleOrGone(View view, boolean b) {
        view.setVisibility(b ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("visible")
    public static void bindVisible(View view, boolean b) {
        view.setVisibility(b ? View.VISIBLE : View.INVISIBLE);
    }

    @BindingConversion
    public static int convertBooleanToVisibility(boolean b) {
        return b ? View.VISIBLE : View.GONE;
    }
}
