package com.arnold.basics.http.log;

import android.text.TextUtils;

import com.arnold.basics.util.JsonUtil;
import com.arnold.basics.util.LogUtil;

import okhttp3.logging.HttpLoggingInterceptor;

public class HttpLogger implements HttpLoggingInterceptor.Logger {


    private static StringBuilder mMessage = new StringBuilder();

    @Override
    public void log(String message) {
        if (TextUtils.isEmpty(message)) {
            LogUtil.i("请求日志null");
            return;
        }

        if (message.startsWith("--> POST")) {
            mMessage.setLength(0);
        }

        if ((message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"))) {
            message = JsonUtil.formatJson(JsonUtil.decodeUnicode(message));
        }

        mMessage.append(message.concat("\n"));
        // 响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            LogUtil.d(mMessage.toString());
        }
    }
}
