package com.gxyan.gmall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @author gxyan
 * @date 2020/8/8 19:01
 */
@Data
public class PurchaseDoneVo {
    /**
     * 采购单id
     */
    private Long id;

    private List<PurchaseItemDoneVo> items;
}
