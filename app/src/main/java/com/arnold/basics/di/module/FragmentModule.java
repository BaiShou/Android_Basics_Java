package com.arnold.basics.di.module;

import android.support.v4.app.Fragment;

import com.arnold.basics.base.BaseActivity;
import com.arnold.basics.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public BaseActivity provideActivity() {
        return (BaseActivity) fragment.getActivity();
    }

}
