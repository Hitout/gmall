package com.gxyan.gmall.member.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author gxyan
 * @date 2020/6/10 23:28
 */
@FeignClient("gmall-coupon")
public interface CouponFeignService {
    @GetMapping("coupon/smscoupon/hello")
    String hello();
}
