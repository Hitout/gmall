package com.gxyan.auth.feign;

import com.gxyan.gmall.common.to.UserLoginVo;
import com.gxyan.auth.vo.UserRegisterVo;
import com.gxyan.gmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gxyan
 * @date 2020/11/30 0:45
 */
@FeignClient("gmall-member")
public interface MemberFeignService {
    @PostMapping(value = "/member/member/register")
    R register(@RequestBody UserRegisterVo registerVo);

    @RequestMapping("member/member/login")
    R login(@RequestBody UserLoginVo vo);
}
