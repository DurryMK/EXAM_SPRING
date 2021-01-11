package com.des.client.core.exception;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/9 14:50
 */
public class RunningException extends Throwable {
    private static final long serialVersionUID = 6348356313629294932L;
    private String msg;
    public RunningException(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
