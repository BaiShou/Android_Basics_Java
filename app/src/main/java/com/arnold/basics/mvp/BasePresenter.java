package com.arnold.basics.mvp;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
