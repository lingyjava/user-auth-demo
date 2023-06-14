package com.ly.user;

import com.ly.api.VerifyCodeServiceI;
import com.ly.dto.user.VerifyCodeDTO;
import com.ly.utils.RedisUtil;
import com.ly.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ling yuan
 * @version 1.0.0
 * @createTime 2023年06月14日 09:11:00
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeServiceI {

    @Autowired
    private RedisUtil redisUtil;

    private static final String USER_VERIFY_CODE_PREFIX = "USER_VERIFY_CODE_";
    private static final int USER_VERIFY_CODE_SIZE  = 4;
    private static final long USER_VERIFY_CODE_TIME_OUT  = 60 * 3;
    private static final String USER_VERIFY_CODE_IMG_FORMAT = "png";

    @Override
    public VerifyCodeDTO get() {
        VerifyCodeUtils code = new VerifyCodeUtils();
        // 生成验证码图片
        BufferedImage image = code.getImage();
        // 验证码四位数字
        String text = code.getText();
        // 存入redis-有效期3分钟
        String verifyCodeKey = USER_VERIFY_CODE_PREFIX + UUID.randomUUID();
        redisUtil.setEx(verifyCodeKey, text, USER_VERIFY_CODE_TIME_OUT, TimeUnit.SECONDS);
        // 进行base64编码
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ImageIO.write(image, USER_VERIFY_CODE_IMG_FORMAT, bos);
            String img = Base64Utils.encodeToString(bos.toByteArray());
            VerifyCodeDTO result = new VerifyCodeDTO();
            result.setKey(verifyCodeKey);
            result.setImg(img);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean valid(VerifyCodeDTO dto) {
        if (dto == null || dto.getKey() == null || dto.getValue() == null) {
            return false;
        }
        if (USER_VERIFY_CODE_SIZE != dto.getValue().length()) {
            return false;
        }
        return dto.getValue().equals(redisUtil.get(dto.getKey()));
    }

    @Override
    public Boolean remove(String key) {
        // return redisUtil.delete(key);
    }
}
