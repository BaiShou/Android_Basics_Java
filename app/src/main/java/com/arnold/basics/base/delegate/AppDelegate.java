package com.arnold.basics.base.delegate;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.arnold.basics.base.App;
import com.arnold.basics.di.component.AppComponent;
import com.arnold.basics.di.component.DaggerAppComponent;
import com.arnold.basics.util.LogUtil;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * ================================================
 * AppDelegate 可以代理 Application 的生命周期,在对应的生命周期,执行对应的逻辑,因为 Java 只能单继承
 * 所以当遇到某些三方库需要继承于它的 Application 的时候,就只有自定义 Application 并继承于三方库的 Application
 * 这时就不用再继承 BaseApplication,只用在自定义Application中对应的生命周期调用AppDelegate对应的方法
 * (Application一定要实现APP接口),框架就能照常运行
 * <p>
 * <p>
 * ================================================
 */
public class AppDelegate implements App, AppLifecycles {

    private Application mApplication;
    private AppComponent mAppComponent;
    @Inject
    @Named("ActivityLifecycle")
    protected Application.ActivityLifecycleCallbacks mActivityLifecycle;
    @Inject
    @Named("ActivityLifecycleForRxLifecycle")
    protected Application.ActivityLifecycleCallbacks mActivityLifecycleForRxLifecycle;

    public AppDelegate(@NonNull Context context) {

    }

    @Override
    public void attachBaseContext(@NonNull Context base) {

    }

    @Override
    public void onCreate(@NonNull Application application) {
        this.mApplication = application;
        LogUtil.init();
        mAppComponent = DaggerAppComponent
                .builder()
                .application(mApplication)//提供application
                .build();
        mAppComponent.inject(this);
        //注册框架内部已实现的 Activity 生命周期逻辑
        mApplication.registerActivityLifecycleCallbacks(mActivityLifecycle);

        //注册框架内部已实现的 RxLifecycle 逻辑
        mApplication.registerActivityLifecycleCallbacks(mActivityLifecycleForRxLifecycle);
    }

    @Override
    public void onTerminate(@NonNull Application application) {
        if (mActivityLifecycle != null) {
            mApplication.unregisterActivityLifecycleCallbacks(mActivityLifecycle);
        }
        if (mActivityLifecycleForRxLifecycle != null) {
            mApplication.unregisterActivityLifecycleCallbacks(mActivityLifecycleForRxLifecycle);
        }
        this.mApplication = null;
        this.mActivityLifecycle = null;
        this.mActivityLifecycleForRxLifecycle = null;

    }


    /**
     * 将 {@link AppComponent} 返回出去, 供其它地方使用, {@link AppComponent} 接口中声明的方法返回的实例, 在 {@link #getAppComponent()} 拿到对象后都可以直接使用
     *
     * @return AppComponent
     * @see
     */
    @NonNull
    @Override
    public AppComponent getAppComponent() {
        return mAppComponent;
    }


}
