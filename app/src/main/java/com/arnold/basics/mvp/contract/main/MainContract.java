package com.arnold.basics.mvp.contract.main;

import com.arnold.basics.mvp.BaseModel;
import com.arnold.basics.mvp.BasePresenter;
import com.arnold.basics.mvp.BaseView;

public interface MainContract {

    interface IView extends BaseView{

    }

    interface Ipresenter extends BasePresenter<IView>{

    }

    interface IModel extends BaseModel{

    }


}
