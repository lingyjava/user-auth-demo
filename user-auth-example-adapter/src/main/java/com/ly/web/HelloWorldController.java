package com.ly.web;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.ly.domain.user.UserMember;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ling yuan
 * @version 1.0.0
 * @createTime 2023年06月15日 10:08:00
 */
@RestController
@RequestMapping("/get")
public class HelloWorldController {

    @PostMapping(value = "/hello")
    public Response hello(@RequestBody UserMember user){
        return SingleResponse.of(String.format("Hello, %s!", user.getUserLoginName()));
    }
}
