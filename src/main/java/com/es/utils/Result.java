package com.es.utils;

import java.io.Serializable;

/**
 * @author luozuyi
 */
public class Result implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 返回值状态
     */
    private String code;
    /**
     * 返回值信息
     */
    private String msg;
    /**
     * 返回值数据
     */
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
