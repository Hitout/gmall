package com.gxyan.gmall.product.vo;

import lombok.Data;

import java.util.List;

/**
 * @author gxyan
 * @date 2020/11/24 23:56
 */
@Data
public class SpuItemAttrGroupVo {
    private String groupName;
    private List<Attr> attrs;
}
