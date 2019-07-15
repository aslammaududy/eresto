package me.aslammaududy.eresto.Helper;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;

public class AndroidBootstrapIconSet extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
    }
}
