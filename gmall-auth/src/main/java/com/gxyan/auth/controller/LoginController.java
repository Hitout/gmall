package com.gxyan.auth.controller;

import com.gxyan.gmall.common.constant.AuthServerConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author gxyan
 * @date 2020/11/29 23:25
 */
@Controller
public class LoginController {
    @GetMapping("/login.html")
    public String loginPage(HttpSession session){
        Object attribute = session.getAttribute(AuthServerConstant.LOGIN_USER);
        if(attribute == null){
            return "login";
        } else {
            return "redirect:http://gxmall.com";
        }
    }
}
