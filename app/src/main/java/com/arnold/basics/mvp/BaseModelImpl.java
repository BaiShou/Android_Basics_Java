package com.arnold.basics.mvp;

import com.arnold.basics.integration.IRepositoryManager;

import javax.inject.Inject;

/**
 * @author：baisoo 创建时间：2018/11/9 19:35
 * 类描述：基类 Model
 * <p>
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BaseModelImpl implements BaseModel {
    protected IRepositoryManager mRepositoryManager = null;

    public BaseModelImpl(IRepositoryManager mRepositoryManager) {
        this.mRepositoryManager = mRepositoryManager;
    }

    /**
     * 在框架中 [BasePresenter.detachView] ()} 时会默认调用 [IModel.onDestroy]
     */
    @Override
    public void onDestroy() {
        mRepositoryManager = null;
    }
}
