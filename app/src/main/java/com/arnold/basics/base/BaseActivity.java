package com.arnold.basics.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.arnold.basics.base.delegate.IActivity;
import com.arnold.basics.di.component.ActivityComponent;
import com.arnold.basics.di.component.AppComponent;
import com.arnold.basics.di.component.DaggerActivityComponent;
import com.arnold.basics.di.module.ActivityModule;
import com.arnold.basics.integration.cache.Cache;
import com.arnold.basics.integration.cache.CacheType;
import com.arnold.basics.integration.lifecycle.ActivityLifecycleable;
import com.arnold.basics.mvp.BasePresenter;
import com.arnold.basics.mvp.BaseView;
import com.arnold.basics.util.AppUtil;
import com.trello.rxlifecycle3.android.ActivityEvent;

import java.security.Policy;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView, IActivity, ActivityLifecycleable {

    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();

    private Cache<String, Object> mCache;
    @Inject
    public BaseActivity mActivity;

    @Inject
    protected P mPresenter;


    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(AppUtil.obtainAppComponentFromContext(this))
                .activityModule(getActivityModule())
                .build();

    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }


    @NonNull
    @Override
    public synchronized Cache<String, Object> provideCache() {
        if (mCache == null) {
            mCache = AppUtil.obtainAppComponentFromContext(this).cacheFactory().build(CacheType.ACTIVITY_CACHE);
        }
        return mCache;
    }

    @NonNull
    @Override
    public final Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getlayoutId());
        ButterKnife.bind(this);
        onViewCreated();
        initView();
        initData();
        //设置监听
        setListener();
    }

    private void onViewCreated() {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        mPresenter = null;
    }

    public View getContentView() {
        return getWindow().getDecorView().findViewById(android.R.id.content);
    }
}
