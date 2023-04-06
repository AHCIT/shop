/**
 *
 */
package com.example.shop.enums;


public enum ReturnCode {

    // 成功
    SUCCESS(0),
    // 失败
    FAIL(-1),
    // 参数问题
    PARAMETER(1000),
    // 账号问题
    ACCOUNT(2000),
    // 首次登陆，需要完善个人信息
    ACCOUNT_FIRST_LOGIN(2001),
    // 账号被冻结
    ACCOUNT_FREEZE(2002),
    // 密码已失效
    PASSWORD_EXPIRE(2003),
    // 密码 快失效 需要提醒
    PASSWORD_EXPIRE_REMINDER(2004),
    // 权限问题
    PERMISSION(3000),
    // session超时
    TIME_OUT(4000),
    // 系统异常
    SYSTEM(5000), SYSTEM_MONGO_NAME_EXCEPTION(50001),
    // 其他问题
    OTHER_ERROR(6000),
    // 定时任务异常
    JOB(7000),
    // 转换异常
    FORMAT(8000);

    private Integer code;

    /**
     * @param code
     */
    private ReturnCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
