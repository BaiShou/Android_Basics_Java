package com.arnold.basics.mvp.ui.activity;

import android.os.Bundle;

import com.arnold.basics.R;
import com.arnold.basics.base.BaseActivity;
import com.arnold.basics.mvp.contract.main.MainContract;
import com.arnold.basics.mvp.presenter.main.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IView {

    @Override
    public int getlayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }
}
