package com.arnold.basics.di.component;

import com.arnold.basics.di.module.ActivityModule;
import com.arnold.basics.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
}
