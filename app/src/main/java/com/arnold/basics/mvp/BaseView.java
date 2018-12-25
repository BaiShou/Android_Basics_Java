package com.arnold.basics.mvp;

import android.text.TextUtils;

import com.arnold.basics.base.BasicsApplication;
import com.arnold.basics.util.ToastUtil;

public interface BaseView {
    /**
     * 显示加载
     */
    default void showLoading() {
    }

    /**
     * 隐藏加载
     */
    default void hideLoading() {
    }

    /**
     * 显示信息
     *
     * @param message 消息内容, 不能为 `null`
     */
    default void showMessage(String message) {
        if (TextUtils.isEmpty(message)) {
            message = "未知错误";
        }
        ToastUtil.show(BasicsApplication.instance, message);
    }

}
