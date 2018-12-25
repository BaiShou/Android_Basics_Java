package com.arnold.basics.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by Tomas on 2018/10/12.
 */

public class ToastUtil {
    private static Toast mToast;

    /**
     * @param context
     * @param msg
     * @param duration
     * @return void
     * @Description描述: 通用的toast提示信息
     */
    public static void show(Context context, CharSequence msg, int duration) {

        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        if(context == null){
            return;
        }
        mToast = Toast.makeText(context, msg, duration);
        mToast.show();
    }

    /**
     * @param context
     * @param msgResID
     * @param duration
     * @return void
     * @Description描述: 通用的toast提示信息
     */
    public static void show(Context context, int msgResID, int duration) {
        show(context, context.getResources().getString(msgResID), duration);
    }

    /**
     * @param context
     * @param msg
     * @return void
     * @Description描述: Toast.LENGTH_SHORT
     */
    public static void show(Context context, CharSequence msg) {
        show(context, msg, Toast.LENGTH_SHORT);
    }

    /**
     * @param context
     * @param msgResID
     * @return void
     * @Description描述: Toast.LENGTH_SHORT
     */
    public static void show(Context context, int msgResID) {
        show(context, msgResID, Toast.LENGTH_SHORT);
    }

    /**
     * @param context
     * @param msg
     * @return void
     * @Description描述: Toast.LENGTH_LONG
     */
    public static void showLong(Context context, CharSequence msg) {
        show(context, msg, Toast.LENGTH_LONG);
    }

    /**
     * @param context
     * @param msgResID
     * @return void
     * @Description描述: Toast.LENGTH_LONG
     */
    public static void showLong(Context context, int msgResID) {
        show(context, msgResID, Toast.LENGTH_LONG);
    }


    public static void show(Context context, Exception e, int msgResID) {
        if (TextUtils.isEmpty(e.toString())) {
            show(context, msgResID, Toast.LENGTH_SHORT);
        } else {
            show(context, e.toString(), Toast.LENGTH_SHORT);
        }
    }


    public static void show(Context context, Exception e, CharSequence msg) {
        if (TextUtils.isEmpty(e.toString())) {
            show(context, msg, Toast.LENGTH_SHORT);
        } else {
            show(context, e.toString(), Toast.LENGTH_SHORT);
        }
    }

}
