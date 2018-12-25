package com.arnold.basics.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenterImpl<T extends BaseView, M extends BaseModel> implements BasePresenter<T> {

    protected T mView = null;
    protected M mModel = null;

    public BasePresenterImpl(M mModel) {
        this.mModel = mModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void onDestroy() {
        mModel = null;
        mView = null;
    }
}
