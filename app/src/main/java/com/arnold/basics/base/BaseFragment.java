package com.arnold.basics.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arnold.basics.base.delegate.IFragment;
import com.arnold.basics.di.component.DaggerFragmentComponent;
import com.arnold.basics.di.component.FragmentComponent;
import com.arnold.basics.di.module.FragmentModule;
import com.arnold.basics.integration.cache.Cache;
import com.arnold.basics.integration.cache.CacheType;
import com.arnold.basics.integration.lifecycle.FragmentLifecycleable;
import com.arnold.basics.mvp.BasePresenter;
import com.arnold.basics.mvp.BaseView;
import com.arnold.basics.util.AppUtil;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.android.FragmentEvent;

import javax.inject.Inject;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView, IFragment, FragmentLifecycleable {

    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();

    private Cache<String, Object> mCache;
    @Inject
    @Nullable
    protected P mPresenter;//如果当前页面逻辑简单, Presenter 可以为 null


    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(AppUtil.obtainAppComponentFromContext(getActivity()))
                .fragmentModule(getFragmentModule())
                .build();

    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @NonNull
    @Override
    public Subject<FragmentEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    public Cache<String, Object> provideCache() {
        if (mCache == null) {
            mCache = AppUtil.obtainAppComponentFromContext(getActivity()).cacheFactory().build(CacheType.FRAGMENT_CACHE);
        }
        return mCache;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, savedInstanceState);
    }

    protected abstract void initInject();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        mPresenter = null;
    }
}
