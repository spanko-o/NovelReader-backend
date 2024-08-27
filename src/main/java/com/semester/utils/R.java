package com.semester.utils;


import lombok.Data;

import java.io.Serializable;

/**
 * <h3>Hypertension</h3>
 *
 * @author HuangJiayu
 * @description <p>通用给前端返回的数据类型</p>
 * @date 2024-03-09 19:59
 **/
@Data
public class R implements Serializable {

    private static final long serialVersionUID = 7988823970938566753L;

    private Integer code;

    private String msg;

    private Object data;

    private void setResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //成功 不返回数据直接返回成功信息
    public static R ok() {
        R r = new R();
        r.setCode(1);
        return r;
    }

    // 成功 返回成功信息
    public static R ok(String msg) {
        R r = new R();
        r.setCode(1);
        r.setMsg(msg);
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.setCode(1);
        r.setData(data);
        return r;
    }

    // 成功 只定义重复信息和data
    public static R ok(String msg, Object data) {
        R r = new R();
        r.setCode(1);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }


    // 单返回失败的状态码
    public static R failure() {
        R r = new R();
        r.setCode(0);
        return r;
    }

    // 返回失败的状态码和失败信息
    public static R failure(String msg) {
        R r = new R();
        r.setCode(0);
        r.setMsg(msg);
        return r;
    }

    public static R failure(Object data) {
        R r = new R();
        r.setCode(0);
        r.setData(data);
        return r;
    }

    // 返回失败的状态码和失败信息和数据
    public static R failure(String msg, Object data) {
        R r = new R();
        r.setCode(0);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
