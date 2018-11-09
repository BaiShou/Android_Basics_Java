package com.arnold.basics.http.converter;

import com.arnold.basics.http.exception.NetApiException;
import com.arnold.basics.mvp.model.BaseResp;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;

/**
 * @author：baisoo
 * 创建时间：2018/11/9 11:17
 * 类描述：自定义响应类  修改做拦截抛出操作 应用到Gson反序列和序列化部分的知识
 *
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        BaseResp httpStatus = gson.fromJson(response, BaseResp.class);

        //验证status返回是否为1
        if (httpStatus.status != 1) {
            value.close();
            //不为-1，表示响应数据不正确，抛出异常
            throw new NetApiException(httpStatus.status,httpStatus.message);
        }


        MediaType contentType = value.contentType();
        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
        InputStream inputStream = new ByteArrayInputStream(response.getBytes());
        Reader reader = new InputStreamReader(inputStream, charset);
        JsonReader jsonReader = gson.newJsonReader(reader);

        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }

}
