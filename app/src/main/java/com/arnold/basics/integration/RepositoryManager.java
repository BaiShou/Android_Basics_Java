package com.arnold.basics.integration;

import android.support.annotation.Nullable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.inject.Inject;

import dagger.Lazy;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class RepositoryManager implements IRepositoryManager {

    @Inject
    Lazy<Retrofit> mRetrofit;

    @Inject
    public RepositoryManager() {
    }

    @Override
    public synchronized <T> T obtainRetrofitService(Class<T> service) {
        return createWrapperService(service);
    }

    /**
     * 根据 https://zhuanlan.zhihu.com/p/40097338 对 Retrofit 进行的优化
     *
     * @param serviceClass ApiService class
     * @param <T>          ApiService class
     * @return ApiService
     */
    private <T> T createWrapperService(Class<T> serviceClass) {
        // 通过二次代理，对 Retrofit 代理方法的调用包进新的 Observable 里在 io 线程执行。
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                new Class<?>[]{serviceClass}, (proxy, method, args) -> {
                    if (method.getReturnType() == Observable.class) {
                        // 如果方法返回值是 Observable 的话，则包一层再返回
                        return Observable.defer(() -> {
                            final T service = getRetrofitService(serviceClass);
                            // 执行真正的 Retrofit 动态代理的方法
                            return ((Observable) getRetrofitMethod(service, method)
                                    .invoke(service, args))
                                    .subscribeOn(Schedulers.io());
                        }).subscribeOn(Schedulers.single());
                    }
                    // 返回值不是 Observable 的话不处理
                    final T service = getRetrofitService(serviceClass);
                    return getRetrofitMethod(service, method).invoke(service, args);
                });
    }

    /**
     * 根据传入的 Class 获取对应的 Retrofit service
     *
     * @param serviceClass ApiService class
     * @param <T>          ApiService class
     * @return ApiService
     */
    private <T> T getRetrofitService(Class<T> serviceClass) {
        return mRetrofit.get().create(serviceClass);
    }

    private <T> Method getRetrofitMethod(T service, Method method) throws NoSuchMethodException {
        return service.getClass().getMethod(method.getName(), method.getParameterTypes());
    }
}
