package com.arnold.basics.di.component;

import com.arnold.basics.base.BaseActivity;
import com.arnold.basics.di.module.FragmentModule;
import com.arnold.basics.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    BaseActivity getActivity();
}
