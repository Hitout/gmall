package com.gxyan.gmall.order.feign;

import com.gxyan.gmall.common.to.OrderItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author gxyan
 * @date 2020/12/6 21:53
 */
@FeignClient("gmall-cart")
public interface CartFeignService {
    @GetMapping("/getCheckedItems")
    List<OrderItemVo> getCheckedItems();
}
