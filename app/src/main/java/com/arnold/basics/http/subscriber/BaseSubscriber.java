package com.arnold.basics.http.subscriber;

import com.arnold.basics.http.exception.ApiException;
import com.arnold.basics.http.exception.CodeException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseSubscriber<T> implements Observer<T> {

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            onError(((ApiException) e).getDisplayMessage(), String.valueOf(((ApiException) e).getCode()));
        } else {
            onError(e.getMessage(), String.valueOf(CodeException.UNKNOWN_ERROR));
        }
        onComplete();
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onComplete() {
    }

    public abstract void onError(String remark, String status);
}
