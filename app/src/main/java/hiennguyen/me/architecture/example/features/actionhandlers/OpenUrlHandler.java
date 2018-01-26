package hiennguyen.me.architecture.example.features.actionhandlers;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;


import com.drextended.actionhandler.action.BaseAction;

import java.util.Locale;

import hiennguyen.me.architecture.example.data.models.Ad;

public class OpenUrlHandler extends BaseAction<Ad> {
    @Override
    public boolean isModelAccepted(Object model) {
        return model instanceof Ad;
    }

    @Override
    public void onFireAction(Context context, @Nullable View view, @Nullable String actionType, @Nullable Ad model) {
        if (model != null) {
            Toast.makeText(context, String.format(Locale.US, "Click on ad %s with url %s", model.getName(), model.getBanner()),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
