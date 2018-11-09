package com.arnold.basics.util;

import android.content.Context;

import com.arnold.basics.base.BasicsApplication;
import com.arnold.basics.di.component.AppComponent;

import dagger.internal.Preconditions;

public class AppUtil {
    /**
     * pxz转换dip
     */
    public static int px2dip(int px) {
        float scale = BasicsApplication.instance.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值
     *
     * @return
     */
    public static int sp2px(int spValue) {
        float fontScale = BasicsApplication.instance.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        float scale = BasicsApplication.instance.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }


    public static AppComponent obtainAppComponentFromContext(Context context) {
        Preconditions.checkNotNull(context, "%s cannot be null", Context.class.getName());
        return ((BasicsApplication) context.getApplicationContext()).getAppComponent();
    }
}
