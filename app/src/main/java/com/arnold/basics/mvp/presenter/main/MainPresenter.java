package com.arnold.basics.mvp.presenter.main;

import com.arnold.basics.mvp.BasePresenterImpl;
import com.arnold.basics.mvp.contract.main.MainContract;

import javax.inject.Inject;

public class MainPresenter extends BasePresenterImpl<MainContract.IView, MainContract.IModel> implements MainContract.Ipresenter {

    @Inject
    public MainPresenter(MainContract.IModel mModel) {
        super(mModel);
    }
}
