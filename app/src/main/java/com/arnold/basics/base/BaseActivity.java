package com.arnold.basics.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.arnold.basics.base.delegate.IActivity;
import com.arnold.basics.di.component.ActivityComponent;
import com.arnold.basics.di.component.AppComponent;
import com.arnold.basics.di.component.DaggerActivityComponent;
import com.arnold.basics.di.module.ActivityModule;
import com.arnold.basics.mvp.BasePresenter;
import com.arnold.basics.mvp.BaseView;
import com.arnold.basics.util.AppUtil;
import com.trello.rxlifecycle3.android.ActivityEvent;

import java.security.Policy;

import javax.inject.Inject;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView, IActivity {

    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutId());
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

    protected abstract void initInject();


}
