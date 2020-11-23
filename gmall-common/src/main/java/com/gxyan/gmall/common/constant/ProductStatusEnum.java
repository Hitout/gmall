package com.gxyan.gmall.common.constant;

/**
 * @author gxyan
 */
public enum ProductStatusEnum {
    /**
     * 新建
     */
    NEW_SPU(0,"新建"),
    /**
     * 商品上架
     */
    SPU_UP(1,"商品上架"),
    /**
     * 商品下架
     */
    SPU_DOWN(2,"商品下架");

    private int code;
    private String msg;

    ProductStatusEnum(int code, String msg) {
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
