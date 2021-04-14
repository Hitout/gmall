package com.gxyan.gmall.order.feign;

import com.gxyan.gmall.common.to.MemberAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author gxyan
 * @date 2020/12/6 22:11
 */
@FeignClient("gmall-member")
public interface MemberFeignService {
    @PostMapping("member/memberreceiveaddress/getAddressByUserId")
    List<MemberAddressVo> getAddressByUserId(@RequestBody Long id);
}
