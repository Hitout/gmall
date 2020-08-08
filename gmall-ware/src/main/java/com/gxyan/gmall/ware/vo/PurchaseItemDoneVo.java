package com.gxyan.gmall.ware.vo;

import lombok.Data;

/**
 * @author gxyan
 * @date 2020/8/8 19:01
 */
@Data
public class PurchaseItemDoneVo {
    private Long itemId;
    private Integer status;
    private String reason;
}
