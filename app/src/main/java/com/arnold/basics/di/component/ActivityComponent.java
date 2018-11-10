package com.arnold.basics.di.component;

import android.app.Application;

import com.arnold.basics.di.module.ActivityModule;
import com.arnold.basics.di.scope.ActivityScope;
import com.arnold.basics.mvp.ui.activity.MainActivity;

import dagger.Component;

/**
 * @author baisoo
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
}
