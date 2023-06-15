package com.ly.web;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.ly.api.UserMemberServiceI;
import com.ly.api.VerifyCodeServiceI;
import com.ly.dto.data.ErrorCode;
import com.ly.dto.user.UserAddCmd;
import com.ly.dto.user.UserLoginCmd;
import com.ly.dto.user.VerifyCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ling yuan
 * @version 1.0.0
 * @createTime 2023年06月08日 09:35:00
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private VerifyCodeServiceI verifyCodeServiceI;
    @Autowired
    private UserMemberServiceI userMemberServiceI;


    /**
     * 用户注册
     * */
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public Response register(@RequestBody UserAddCmd cmd, HttpServletRequest request, HttpServletResponse response) {
        userMemberServiceI.register(cmd);
        return Response.buildSuccess();
    }

    /**
     * 用户登录
     * */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody UserLoginCmd cmd, HttpServletRequest request, HttpServletResponse response) {
        return userMemberServiceI.login(cmd);
    }

    /**
     * 用户登录/注册校验码生成
     */
    @RequestMapping(path = "/getVerifyCodePic",method = RequestMethod.GET)
    public Response getVerifyCodePic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerifyCodeDTO result = verifyCodeServiceI.get();
        return result != null ? SingleResponse.of(result) : SingleResponse.buildFailure(ErrorCode.B_OTHER_EXCEPTION.getErrCode(), "生成验证码失败");
    }

}
