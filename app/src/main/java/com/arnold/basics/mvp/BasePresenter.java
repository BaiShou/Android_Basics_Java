package com.arnold.basics.mvp;

import android.app.Activity;

public interface BasePresenter<T extends BaseView> {

    /**
     * 做一些初始化操作
     */
    void onStart();

    void attachView(T view);

    /**
     * 在框架中 {@link Activity#onDestroy()} 时会默认调用 {@link RxPresenter#onDestroy()}
     */
    void onDestroy();
}
