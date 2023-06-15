package com.ly.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ling yuan
 * @version 1.0.0
 * @createTime 2023年06月13日 09:26:00
 */
@Getter
@Setter
@ToString
public class UserLoginCmd {

    private String userLoginName;

    private String password;

    private VerifyCodeDTO verifyCodeDTO;
}
