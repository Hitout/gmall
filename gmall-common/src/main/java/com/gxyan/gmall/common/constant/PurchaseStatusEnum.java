package com.gxyan.gmall.common.constant;

/**
 * @author gxyan
 */
public enum PurchaseStatusEnum {
    /**
     * 新建
     */
    CREATED(0,"新建"),
    /**
     * 已分配
     */
    ASSIGNED(1,"已分配"),
    /**
     * 已领取
     */
    RECEIVE(2,"已领取"),
    /**
     * 已完成
     */
    FINISH(3,"已完成"),
    /**
     * 有异常
     */
    HAS_ERROR(4,"有异常");

    private int code;
    private String msg;

    PurchaseStatusEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
