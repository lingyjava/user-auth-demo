package com.ly.web;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.ly.dto.data.ErrorCode;
import com.ly.utils.RedisUtil;
import com.ly.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ling yuan
 * @version 1.0.0
 * @createTime 2023年06月08日 09:35:00
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 用户登录/注册校验码生成
     * 生成验证码后，将本次生成验证码操作存入redis中，有效期为3分钟
     * 键值规则为  USER_VERIFYCODE_SESSION + UUID : 4位数字验证码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(path = "/getVerifyCodePic",method = RequestMethod.GET)
    public Response getVerifyCodePic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String userVerifyCodePrefix = "USER_VERIFY_CODE_";
        Map<String, String> result = new HashMap<>(2);
        VerifyCodeUtils code = new VerifyCodeUtils();
        // 生成验证码图片
        BufferedImage image = code.getImage();
        // 获取验证码四位数字
        String text = code.getText();
        System.out.println(text);
        // 验证码-键值对存入分别存入redis
        String verifyCodeKey = userVerifyCodePrefix + UUID.randomUUID();
        redisUtil.setEx(verifyCodeKey, text, 60 * 3, TimeUnit.SECONDS);
        // 进行base64编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try{
            ImageIO.write(image, "png", bos);
            String string = Base64Utils.encodeToString(bos.toByteArray());
            result.put("key", verifyCodeKey);
            result.put("image", string);
            return SingleResponse.of(result);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            bos.close();
        }
        return SingleResponse.buildFailure(ErrorCode.B_OTHER_EXCEPTION.getErrCode(), "生成验证码失败");
    }
}
