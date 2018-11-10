package com.arnold.basics.di.module;

import com.arnold.basics.base.BaseActivity;
import com.arnold.basics.di.scope.ActivityScope;
import com.arnold.basics.mvp.contract.main.MainContract;
import com.arnold.basics.mvp.model.impl.main.MainModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    public MainContract.IModel provideMainModel(MainModel mainModel) {
        return mainModel;
    }

    @Provides
    @ActivityScope
    public BaseActivity provideActivity() {
        return activity;
    }


}
