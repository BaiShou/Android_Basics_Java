package com.arnold.basics.base.delegate;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.arnold.basics.di.component.ActivityComponent;
import com.arnold.basics.integration.ActivityLifecycle;
import com.arnold.basics.integration.cache.Cache;
import com.arnold.basics.integration.cache.LruCache;

public interface IActivity {
    /**
     * 提供在 {@link Activity} 生命周期内的缓存容器, 可向此 {@link Activity} 存取一些必要的数据
     * 此缓存容器和 {@link Activity} 的生命周期绑定, 如果 {@link Activity} 在屏幕旋转或者配置更改的情况下
     * 重新创建, 那此缓存容器中的数据也会被清空, 如果你想避免此种情况请使用 <a href="https://github.com/JessYanCoding/LifecycleModel">LifecycleModel</a>
     *
     * @return like {@link LruCache}
     */
    @NonNull
    Cache<String, Object> provideCache();

    void initInject();

    /**
     * 是否使用 EventBus
     * Arms 核心库现在并不会依赖某个 EventBus, 要想使用 EventBus, 还请在项目中自行依赖对应的 EventBus
     * 现在支持两种 EventBus, greenrobot 的 EventBus 和畅销书 《Android源码设计模式解析与实战》的作者 何红辉 所作的 AndroidEventBus
     * 确保依赖后, 将此方法返回 true, Arms 会自动检测您依赖的 EventBus, 并自动注册
     * 这种做法可以让使用者有自行选择三方库的权利, 并且还可以减轻 Arms 的体积
     *
     * @return 返回 {@code true}, Arms 会自动注册 EventBus
     */
    default boolean useEventBus() {
        return false;
    }

    default void setListener() {
    }

    default void initData() {
    }

    int getlayoutId();

    void initView();
    /**
     * 这个Activity是否会使用Fragment,框架会根据这个属性判断是否注册{@link android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks}
     * 如果返回false,那意味着这个Activity不需要绑定Fragment,那你再在这个Activity中绑定继承于 {@link BaseFragment} 的Fragment将不起任何作用
     *
     * @return
     * @see ActivityLifecycle#registerFragmentCallbacks
     */
    default boolean useFragment() {
        return false;
    }
}
