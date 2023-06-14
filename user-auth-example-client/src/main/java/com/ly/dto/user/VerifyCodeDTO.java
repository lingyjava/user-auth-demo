package com.ly.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ling yuan
 * @version 1.0.0
 * @createTime 2023年06月14日 09:32:00
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VerifyCodeDTO implements Serializable {

    private String key;

    private String value;

    private String img;
}
