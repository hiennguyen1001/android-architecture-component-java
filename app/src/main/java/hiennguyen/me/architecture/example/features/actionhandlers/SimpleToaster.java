package hiennguyen.me.architecture.example.features.actionhandlers;

import android.content.Context;
import android.widget.Toast;

import hiennguyen.me.architecture.example.features.delegates.Toaster;


public class SimpleToaster implements Toaster {
    @Override
    public void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
