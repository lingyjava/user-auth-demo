package com.ly.api;

import com.alibaba.cola.dto.Response;
import com.ly.dto.user.UserAddCmd;
import com.ly.dto.user.UserLoginCmd;

/**
 * 用户管理API(接口定义)
 *
 * @author Ly
 * @date 2023/6/9 16:08.
 */
public interface UserMemberServiceI {

    void register(UserAddCmd cmd);

    Response login(UserLoginCmd cmd);

}
