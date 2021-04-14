package com.gxyan.gmall.common.to.mq;

import lombok.Data;

/**
 * @author gxyan
 * @date 2020/12/12
 */
@Data
public class StockLockedTo {
    private Long id;
    private StockDetailTo detailTo;
}
