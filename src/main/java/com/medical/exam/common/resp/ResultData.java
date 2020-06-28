package com.medical.exam.common.resp;

import java.io.Serializable;

/**
 * 通用泛型响应
 *
 * @param <T>
 * @author Wu
 */
public class ResultData<T> implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    /**
     * 响应状态值
     */
    private int status;
    /**
     * 响应数据
     */
    public T data;
    /**
     * 响应提示
     */
    public String msg;

    public ResultData() {
    }

    public ResultData(int status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public ResultData<T> setStatus(int status) {
        this.status = status;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultData<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultData<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public ResultData clone() {
        ResultData resultData = null;
        try {
            resultData = (ResultData) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return resultData;
    }
}
