package com.arnold.basics.base.delegate;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * @author：baisoo
 * 创建时间：2018/11/9 14:20
 * 类描述：用于代理 [Application] 的生命周期
 *
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface AppLifecycles {
    void attachBaseContext(@NonNull Context base);

    void onCreate(@NonNull Application application);

    void onTerminate(@NonNull Application application);
}
