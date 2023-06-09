DROP TABLE IF EXISTS user_member;
CREATE TABLE user_member(
    `user_id` BIGINT NOT NULL AUTO_INCREMENT  COMMENT '用户id' ,
    `user_login_name` VARCHAR(255) NOT NULL   COMMENT '登录名' ,
    `user_real_name` VARCHAR(255)    COMMENT '实际姓名' ,
    `email` VARCHAR(255)    COMMENT '邮箱' ,
    `mobile` VARCHAR(255)    COMMENT '手机' ,
    `password` VARCHAR(255) NOT NULL   COMMENT '密码' ,
    `salt` VARCHAR(32) NOT NULL   COMMENT '盐' ,
    `create_by` BIGINT NOT NULL   COMMENT '创建人id' ,
    `create_by_name` VARCHAR(255) NOT NULL   COMMENT '创建人' ,
    `create_time` DATETIME NOT NULL   COMMENT '创建时间' ,
    `update_by` BIGINT    COMMENT '修改人id' ,
    `update_by_name` VARCHAR(255)    COMMENT '修改人' ,
    `update_time` DATETIME    COMMENT '修改时间' ,
     PRIMARY KEY (user_id)
)  COMMENT = '';
