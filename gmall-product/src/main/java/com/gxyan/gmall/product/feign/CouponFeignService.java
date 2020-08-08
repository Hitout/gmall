package com.gxyan.gmall.product.feign;

import com.gxyan.gmall.common.to.SkuReductionTo;
import com.gxyan.gmall.common.to.SpuBoundTo;
import com.gxyan.gmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author gxyan
 * @date 2020/8/7 23:38
 */
@FeignClient("gmall-coupon")
public interface CouponFeignService {
    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);


    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}
