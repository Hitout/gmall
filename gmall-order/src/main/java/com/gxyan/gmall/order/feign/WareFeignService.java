package com.gxyan.gmall.order.feign;

import com.gxyan.gmall.common.to.WareSkuLockVo;
import com.gxyan.gmall.common.utils.R;
import com.gxyan.gmall.common.to.FareVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author gxyan
 * @date 2020/12/6 22:09
 */
@FeignClient("gmall-ware")
public interface WareFeignService {
    @PostMapping("ware/waresku/hasstock")
    R getSkuHasStocks(@RequestBody List<Long> ids);

    @PostMapping("ware/waresku/lock/order")
    R orderLockStock(@RequestBody WareSkuLockVo lockVo);

    @RequestMapping("ware/wareinfo/fare/{addrId}")
    FareVo getFare(@PathVariable("addrId") Long addrId);
}
