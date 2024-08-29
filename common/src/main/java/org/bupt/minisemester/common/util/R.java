package org.bupt.minisemester.common.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class R implements Serializable {

    private static final long serialVersionUID = 7988823970938566753L;

    private Integer code;  // 状态码
    private String msg;    // 返回消息
    private Object data;   // 返回数据

    // 设置返回结果的状态码和消息
    private void setResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // 成功 不返回数据直接返回成功信息，默认状态码 200
    public static R ok() {
        R r = new R();
        r.setCode(200);  // 默认成功状态码 200
        return r;
    }

    // 成功 返回成功信息，默认状态码 200
    public static R ok(String msg) {
        R r = new R();
        r.setCode(200);  // 默认成功状态码 200
        r.setMsg(msg);
        return r;
    }

    // 成功 返回数据，默认状态码 200
    public static R ok(Object data) {
        R r = new R();
        r.setCode(200);  // 默认成功状态码 200
        r.setData(data);
        return r;
    }

    // 成功 返回自定义信息和数据，默认状态码 200
    public static R ok(String msg, Object data) {
        R r = new R();
        r.setCode(200);  // 默认成功状态码 200
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    // 成功 返回自定义状态码和消息，不带数据
    public static R ok(int code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    // 成功 返回自定义状态码、消息和数据
    public static R ok(int code, String msg, Object data) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    // 失败 返回默认状态码 500
    public static R failure() {
        R r = new R();
        r.setCode(500);  // 默认失败状态码 500
        return r;
    }

    // 失败 返回自定义消息，默认状态码 500
    public static R failure(String msg) {
        R r = new R();
        r.setCode(500);  // 默认失败状态码 500
        r.setMsg(msg);
        return r;
    }

    // 失败 返回自定义消息和数据，默认状态码 500
    public static R failure(String msg, Object data) {
        R r = new R();
        r.setCode(500);  // 默认失败状态码 500
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    // 失败 返回自定义状态码和消息
    public static R failure(int code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    // 失败 返回自定义状态码、消息和数据
    public static R failure(int code, String msg, Object data) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
