package com.arnold.basics.mvp;

public interface BaseView {
    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示信息
     *
     * @param message 消息内容, 不能为 `null`
     */
    void showMessage(String message);

}
