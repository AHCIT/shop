package com.example.shop.exception;

/**
 * @Author: ahzhouli@outlook.com
 * @Description: 分布式锁获取异常
 * @CreateDate: 2023/4/5 20:06
 * @UpdateUser: zhouli
 * @UpdateDate: 2023/4/5 20:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GetLockException extends RuntimeException {
    private String msg;

    public GetLockException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
