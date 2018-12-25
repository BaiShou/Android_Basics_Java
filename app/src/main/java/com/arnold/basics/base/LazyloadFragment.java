package com.arnold.basics.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * 创建人：baisoo
 * 创建时间：2018/12/21 14:56
 * 类描述：Fragment基类，封装了懒加载的实现
 * <p>
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class LazyloadFragment extends Fragment {
    /**
     * 是否初始化过布局
     */
    protected boolean isViewInitiated;
    /**
     * 当前界面是否可见
     */
    protected boolean isVisibleToUser;
    /**
     * 是否加载过数据
     */
    protected boolean isDataInitiated ;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            prepareFetchData();
        }
    }

    /**
     * 懒加载
     */
    public void fetchData() {
    }

    public void prepareFetchData() {
        if (isVisibleToUser && isViewInitiated && !isDataInitiated) {
            fetchData();
            isDataInitiated = true;
        }
    }

}
