package com.ly.user;

import com.alibaba.cola.dto.Response;
import com.ly.api.UserMemberServiceI;
import com.ly.dto.user.UserAddCmd;
import com.ly.dto.user.UserLoginCmd;
import org.springframework.stereotype.Service;

/**
 * @author ling yuan
 * @version 1.0.0
 * @createTime 2023年06月13日 09:53:00
 */
@Service
public class UserMemberServiceImpl implements UserMemberServiceI {

    @Override
    public void register(UserAddCmd cmd) {

    }

    @Override
    public Response login(UserLoginCmd cmd) {
        return null;
    }
}
