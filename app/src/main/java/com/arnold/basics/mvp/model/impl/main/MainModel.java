package com.arnold.basics.mvp.model.impl.main;

import com.arnold.basics.di.scope.ActivityScope;
import com.arnold.basics.integration.IRepositoryManager;
import com.arnold.basics.mvp.BaseModel;
import com.arnold.basics.mvp.BaseModelImpl;
import com.arnold.basics.mvp.contract.main.MainContract;

import javax.inject.Inject;

@ActivityScope
public class MainModel extends BaseModelImpl implements MainContract.IModel {

    @Inject
    public MainModel(IRepositoryManager mRepositoryManager) {
        super(mRepositoryManager);
    }

}
