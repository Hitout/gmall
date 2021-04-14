package com.gxyan.gmall.order.feign;

import com.gxyan.gmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gxyan
 * @date 2021/4/13
 */
@FeignClient("gmall-product")
public interface ProductFeignService {
    @RequestMapping("product/spuinfo/skuId/{skuId}")
    R getSpuBySkuId(@PathVariable("skuId") Long skuId);
}
