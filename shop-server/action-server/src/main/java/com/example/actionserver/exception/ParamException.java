package com.example.actionserver.exception;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: TODO
 * @CreateDate: 2023/4/6 00:17
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/6 00:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ParamException extends RuntimeException {
    private String msg;

    public ParamException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

