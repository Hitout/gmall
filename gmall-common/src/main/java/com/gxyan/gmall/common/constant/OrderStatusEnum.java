package com.gxyan.gmall.common.constant;

/**
 * @author gxyan
 */
public enum OrderStatusEnum {
    /** 已取消 */
    CANCEL(4, "已取消"),
    /** 待付款 */
    CREATE_NEW(0, "待付款"),
    /** 已付款 */
    PAYED(1, "已付款"),
    /** 已完成 */
    RECEIVED(3, "已完成"),
    /** 已发货 */
    SENDED(2, "已发货"),
    /** 售后完成 */
    SERVICED(6, "售后完成"),
    /** 售后中 */
    SERVICING(5, "售后中");

    private int code;
    private String message;

    OrderStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
