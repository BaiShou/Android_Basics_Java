package com.arnold.basics.http.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        if (originalRequest != null) {
            return chain.proceed(originalRequest.newBuilder()
                    .header("Content-Type", "text/plain; charset=utf-8")
                    .header("Accept", "*/*")
//                    .header("accesstoken", tokenValue)
                    .build());
        }
        return chain.proceed(chain.request());
    }
}
