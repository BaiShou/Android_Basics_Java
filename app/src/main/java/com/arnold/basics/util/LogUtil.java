package com.arnold.basics.util;

import android.support.annotation.Nullable;

import com.arnold.basics.BuildConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

public class LogUtil {
    private LogUtil() {

    }

    public static void init() {
        PrettyFormatStrategy power = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  //（可选）是否显示线程信息。 默认值为true
                .methodCount(2)         // （可选）要显示的方法行数。 默认2
                .methodOffset(7)        // （可选）隐藏内部方法调用到偏移量。 默认5
                .tag("POWER")           //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(power) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.LOG_DEBUG;
            }
        });
    }

    public static void d(String message) {
        Logger.d(message);
    }

    public static void i(String message) {
        Logger.i(message);
    }

    public static void w(String message, Throwable e) {
        Logger.w(message + "：" + e.toString());
    }

    public static void w(String message) {
        Logger.w(message);
    }

    public static void e(String message, Throwable e) {
        Logger.e(e, message);
    }


    public static void json(String json) {
        Logger.json(json);
    }

}
