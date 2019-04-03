package com.sf.appTv.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Response<T> {
    private int code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)//如果为null，不返回
    private T data;

    public Response() {
    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
