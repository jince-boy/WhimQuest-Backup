package com.whim.common.core.enums;

import lombok.Getter;

/**
 * @author Jince
 * @since: 2023.12.03 下午 10:00
 * @description: 邮箱模板枚举类
 */
@Getter
public enum EmailTemplateEnums {

    VERIFY_CODE_EMAIL_TEMPLATE("verifyCodeTemplate", "验证码模板"),
    ;
    private final String name;
    private final String desc;

    EmailTemplateEnums(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
