package com.example.shop.dto;

import com.example.shop.enums.ReturnCode;

public class BaseReturnDto<T> {


    /**
     * 标识 大于0成功，<0 失败
     */
    private int flag;
    /**
     * 提示消息
     */
    private String msg;
    /**
     * 返回的数据
     */
    private T data;

    public BaseReturnDto() {
        this.flag = ReturnCode.SUCCESS.getCode();
        this.msg = "ok";
    }

    public BaseReturnDto(T data) {
        this.flag = ReturnCode.SUCCESS.getCode();
        this.msg = "ok";
        this.data = data;
    }

    public BaseReturnDto(int flag) {
        this.flag = flag;
        this.msg = "ok";
    }

    public BaseReturnDto(int flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public BaseReturnDto(int flag, String msg, T data) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

    public static <T> BaseReturnDto<T> ofSuccess() {
        BaseReturnDto<T> brd = new BaseReturnDto<>();
        brd.setFlag(ReturnCode.SUCCESS.getCode());
        brd.setMsg("ok");
        return brd;
    }

    public static <T> BaseReturnDto<T> ofSuccess(T data) {
        BaseReturnDto<T> brd = new BaseReturnDto<>();
        brd.setFlag(ReturnCode.SUCCESS.getCode());
        brd.setMsg("ok");
        brd.setData(data);
        return brd;
    }

    public static <T> BaseReturnDto<T> ofSuccessMsg(String msg) {
        BaseReturnDto<T> brd = new BaseReturnDto<>();
        brd.setFlag(ReturnCode.SUCCESS.getCode());
        brd.setMsg(msg);
        return brd;
    }

    public static <T> BaseReturnDto<T> ofFail() {
        BaseReturnDto<T> brd = new BaseReturnDto<>();
        brd.setFlag(ReturnCode.FAIL.getCode());
        brd.setMsg("fail");
        return brd;
    }

    public static <T> BaseReturnDto<T> ofFail(String msg) {
        BaseReturnDto<T> brd = new BaseReturnDto<>();
        brd.setFlag(ReturnCode.FAIL.getCode());
        brd.setMsg(msg);
        return brd;
    }

    public static <T> BaseReturnDto<T> ofFail(Integer flag, String msg) {
        BaseReturnDto<T> brd = new BaseReturnDto<>();
        brd.setFlag(flag);
        brd.setMsg(msg);
        return brd;
    }

    public static <T> BaseReturnDto<T> ofThrowable(int flag, Throwable throwable) {
        BaseReturnDto<T> brd = new BaseReturnDto<>();
        brd.setFlag(flag);
        brd.setMsg(throwable.getClass().getName() + ", " + throwable.getMessage());
        return brd;
    }

    public static <T> BaseReturnDto<T> ofThrowable(Throwable throwable) {
        BaseReturnDto<T> brd = new BaseReturnDto<>();
        brd.setFlag(ReturnCode.FAIL.getCode());
        brd.setMsg(throwable.getMessage());
        return brd;
    }

    public static <T> BaseReturnDto<T> ofException(T data) {
        BaseReturnDto<T> brd = new BaseReturnDto<>();
        brd.setFlag(ReturnCode.PARAMETER.getCode());
        brd.setMsg("ok");
        brd.setData(data);
        return brd;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
