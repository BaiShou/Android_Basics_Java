package com.arnold.basics.base;

import android.app.Application;
import android.content.Context;

import com.arnold.basics.base.delegate.AppDelegate;
import com.arnold.basics.base.delegate.AppLifecycles;
import com.arnold.basics.di.component.AppComponent;

public class BasicsApplication extends Application implements App {

    private AppLifecycles mAppDelegate = null;

    public static BasicsApplication instance;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        instance = this;
        if (mAppDelegate == null) {
            this.mAppDelegate = new AppDelegate(base);
        }
        mAppDelegate.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppDelegate.onCreate(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mAppDelegate.onTerminate(this);
    }

    @Override
    public AppComponent getAppComponent() {
        return ((App) mAppDelegate).getAppComponent();
    }
}
