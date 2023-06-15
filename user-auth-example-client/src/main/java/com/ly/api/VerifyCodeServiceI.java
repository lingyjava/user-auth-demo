package com.ly.api;

import com.ly.dto.user.VerifyCodeDTO;

/**
 * @author ling yuan
 * @version 1.0.0
 * @createTime 2023年06月14日 09:12:00
 */
public interface VerifyCodeServiceI {

    VerifyCodeDTO get();

    Boolean valid(VerifyCodeDTO dto);

    void del(String key);
}