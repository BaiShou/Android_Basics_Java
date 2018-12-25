package com.arnold.basics.util;

import com.arnold.basics.http.exception.FactoryException;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxUtil {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一错误处理
     *
     * @param <T>
     * @return
     */
    public static <T> Function<Throwable, ObservableSource<T>> rxErrorHelper() {
        return throwable -> Observable.error(FactoryException.analysisExcetpion(throwable));

    }
}
