package com.gxyan.gmall.common.constant;

/**
 * @author gxyan
 */

public enum PurchaseDetailStatusEnum {
    /**
     * 新建
     */
    CREATED(0,"新建"),
    /**
     * 已分配
     */
    ASSIGNED(1,"已分配"),
    /**
     * 正在采购
     */
    BUYING(2,"正在采购"),
    /**
     * 已完成
     */
    FINISH(3,"已完成"),
    /**
     * 采购失败
     */
    HAS_ERROR(4,"采购失败");
    private int code;
    private String msg;

    PurchaseDetailStatusEnum(int code,String msg){
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
