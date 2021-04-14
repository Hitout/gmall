package com.gxyan.gmall.ware.feign;

import com.gxyan.gmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author gxyan
 * @date 2021/4/14
 */
@FeignClient("gmall-order")
public interface OrderFeignService {
    @GetMapping("order/order/getByOrderSn/{orderSn}")
    R getByOrderSn(@PathVariable("orderSn") String orderSn);
}
