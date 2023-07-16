package com.example.actionserver.exception;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: 重复操作异常
 * @CreateDate: 2023/4/5 19:41
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 19:41
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DuplicateException extends RuntimeException {
    private String msg;

    public DuplicateException(String msg) {
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
