package com.ly.user;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import com.ly.api.UserMemberServiceI;
import com.ly.api.VerifyCodeServiceI;
import com.ly.domain.user.UserMember;
import com.ly.dto.user.UserAddCmd;
import com.ly.dto.user.UserLoginCmd;
import com.ly.dto.user.UserMemberDTO;
import com.ly.utils.PasswordUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author ling yuan
 * @version 1.0.0
 * @createTime 2023年06月13日 09:53:00
 */
@Service
public class UserMemberServiceImpl implements UserMemberServiceI {

    @Autowired
    private UserMemberMapper userMemberMapper;

    private final int MIN_PASSWORD_LENGTH = 8;

    @Autowired
    private VerifyCodeServiceI verifyCodeServiceI;

    @Override
    public void register(UserAddCmd cmd) {
        if (cmd == null || cmd.getUserMemberDTO() == null || cmd.getUserMemberDTO().getUserLoginName() == null || cmd.getUserMemberDTO().getPassword() == null) {
            throw new BizException("500", "缺少必要参数");
        }
        UserMemberDTO userMemberDTO = cmd.getUserMemberDTO();
        UserMember userMember = new UserMember();
        BeanUtils.copyProperties(userMemberDTO, userMember);
        // 校验验证码
        if (!verifyCodeServiceI.valid(cmd.getVerifyCodeDTO())) {
            throw new BizException("500", "无效的验证码");
        }
        // 校验密码
        if (userMember.getPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new BizException("500", "密码长度不允许小于8位");
        }
        // 校验用户名
        UserMember query = new UserMember();
        query.setUserLoginName(userMember.getUserLoginName());
        List<UserMember> members = userMemberMapper.queryAllByLimit(query, PageRequest.of(0, 1));
        if (!CollectionUtils.isEmpty(members)) {
            throw new BizException("500", "用户名已存在");
        }

        // 密码加密
        String salt = PasswordUtil.salt();
        String encryptPassword = PasswordUtil.encrypt(userMember.getPassword(), salt);
        userMember.setPassword(encryptPassword);
        userMember.setSalt(salt);
        userMemberMapper.insert(userMember);

    }

    @Override
    public Response login(UserLoginCmd cmd) {
        if (cmd == null || cmd.getUserLoginName() == null || cmd.getPassword() == null) {
            throw new BizException("500", "缺少必要参数");
        }
        // 校验密码
        if (cmd.getPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new BizException("500", "密码长度不允许小于8位");
        }

        return null;
    }
}
