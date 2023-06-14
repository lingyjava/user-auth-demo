package com.ly.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ling yuan
 * @version 1.0.0
 * @createTime 2023年06月13日 09:24:00
 */
@Getter
@Setter
@ToString
public class UserAddCmd {

    private UserMemberDTO userMemberDTO;

    private VerifyCodeDTO verifyCodeDTO;

}
