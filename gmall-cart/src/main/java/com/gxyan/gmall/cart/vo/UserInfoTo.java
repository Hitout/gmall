package com.gxyan.gmall.cart.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author gxyan
 * @date 2020/12/5 18:10
 */
@Data
@ToString
public class UserInfoTo {
    private Long userId;
    private String userKey;
    private boolean tempUser = false;
}
