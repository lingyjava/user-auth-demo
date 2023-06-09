package com.ly.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ling yuan
 * @version 1.0.0
 * @createTime 2023年06月09日 15:27:00
 */
@Data
@ApiModel(value = "",description = "")
public class UserMemberDTO implements Serializable,Cloneable{
    /** 用户id */
    @ApiModelProperty(name = "用户id",notes = "")
    private Long userId ;
    /** 登录名 */
    @ApiModelProperty(name = "登录名",notes = "")
    private String userLoginName ;
    /** 实际姓名 */
    @ApiModelProperty(name = "实际姓名",notes = "")
    private String userRealName ;
    /** 邮箱 */
    @ApiModelProperty(name = "邮箱",notes = "")
    private String email ;
    /** 手机 */
    @ApiModelProperty(name = "手机",notes = "")
    private String mobile ;
    /** 密码 */
    @ApiModelProperty(name = "密码",notes = "")
    private String password ;
    /** 盐 */
    @ApiModelProperty(name = "盐",notes = "")
    private String salt ;
    /** 创建人id */
    @ApiModelProperty(name = "创建人id",notes = "")
    private Long createBy ;
    /** 创建人 */
    @ApiModelProperty(name = "创建人",notes = "")
    private String createByName ;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间",notes = "")
    private Date createTime ;
    /** 修改人id */
    @ApiModelProperty(name = "修改人id",notes = "")
    private Long updateBy ;
    /** 修改人 */
    @ApiModelProperty(name = "修改人",notes = "")
    private String updateByName ;
    /** 修改时间 */
    @ApiModelProperty(name = "修改时间",notes = "")
    private Date updateTime ;


    @Override
    public UserMemberDTO clone() {
        try {
            UserMemberDTO clone = (UserMemberDTO) super.clone();
            // TODO: 复制此处的可变状态，这样此克隆就不能更改初始克隆的内部
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}