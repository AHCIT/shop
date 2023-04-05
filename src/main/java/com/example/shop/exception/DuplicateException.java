package com.example.shop.exception;

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
    private Integer code;
    private String msg;

    public DuplicateException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public DuplicateException(String message, Integer code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public DuplicateException(String message, Throwable cause, Integer code, String msg) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
    }

    public DuplicateException(Throwable cause, Integer code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public DuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
