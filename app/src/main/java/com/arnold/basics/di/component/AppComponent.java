package com.arnold.basics.di.component;

import android.app.Application;

import com.arnold.basics.base.delegate.AppDelegate;
import com.arnold.basics.di.module.AppModule;
import com.arnold.basics.di.module.ClientModule;
import com.arnold.basics.integration.IRepositoryManager;
import com.arnold.basics.integration.cache.Cache;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ClientModule.class})
public interface AppComponent {

//    public Application application();


    /**
     * 用于管理网络请求层, 以及数据缓存层
     *
     * @return [IRepositoryManager]
     */
    public IRepositoryManager repositoryManager();

    /**
     * 用于创建框架所需缓存对象的工厂
     *
     * @return [Cache.Factory]
     */
    public Cache.Factory cacheFactory();

    public void inject(Application application);

    public void inject(AppDelegate delegate);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
