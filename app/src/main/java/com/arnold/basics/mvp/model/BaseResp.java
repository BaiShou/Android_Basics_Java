package com.arnold.basics.mvp.model;

import java.io.Serializable;

public class BaseResp<T> implements Serializable {

    public int status;
    public String message;
    public T data;


}
