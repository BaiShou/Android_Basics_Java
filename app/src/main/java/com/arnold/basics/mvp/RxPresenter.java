package com.arnold.basics.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class RxPresenter<T extends BaseView, M extends IModel> implements BasePresenter<T> {

    protected T mView = null;
    protected M mModel = null;
    protected CompositeDisposable mCompositeDisposable = null;

    public RxPresenter(M mModel) {
        this.mModel = mModel;
    }

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    protected void removeSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null || subscription == null) {
            return;
        }
        mCompositeDisposable.remove(subscription);
    }


    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        unSubscribe();
        mModel = null;
        mView = null;

    }
}
