package com.arnold.basics.di.module;

import com.arnold.basics.base.BaseActivity;
import com.arnold.basics.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public BaseActivity provideActivity() {
        return activity;
    }


}
