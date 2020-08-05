package com.gxyan.gmall.common.constant;

/**
 * @author gxyan
 */

public enum AttrTypeEnum {
    /**
     * 基本属性
     */
    ATTR_TYPE_BASE(1,"基本属性"),
    /**
     * 销售属性
     */
    ATTR_TYPE_SALE(0,"销售属性");

    private int code;
    private String msg;

    AttrTypeEnum(int code, String msg){
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
