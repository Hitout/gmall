package com.gxyan.gmall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @author gxyan
 * @date 2020/8/8 18:24
 */
@Data
public class MergeVo {
    private Long purchaseId;
    private List<Long> items;
}
