package com.alex.spring.mvc;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by gaojun on 16/3/19.
 */
public class CommonResult {
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private String msg;

    public List getData() {
        return Data;
    }

    public void setData(List data) {
        Data = data;
    }

    private List Data;
}
