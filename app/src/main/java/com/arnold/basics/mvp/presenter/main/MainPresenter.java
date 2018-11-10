package com.arnold.basics.mvp.presenter.main;

import com.arnold.basics.mvp.RxPresenter;
import com.arnold.basics.mvp.contract.main.MainContract;

import javax.inject.Inject;

public class MainPresenter extends RxPresenter<MainContract.IView, MainContract.IModel> implements MainContract.Ipresenter {

    @Inject
    public MainPresenter(MainContract.IModel mModel) {
        super(mModel);
    }
}
