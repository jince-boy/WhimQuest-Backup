package com.whim;

import com.whim.common.core.enums.EmailTemplateEnums;
import com.whim.common.utils.EmailHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Administrator-CCENOTE
 * @since: 2023/12/14 14:01
 * @description: 邮箱测试类
 */
@SpringBootTest
@Slf4j
public class WhimQuestEmailTests {
    @Resource
    private EmailHelper emailHelper;

    @Test
    void sendEmail() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "2766401256");
        map.put("verifyCode", "JS2F");
        map.put("minutes", "3");
        emailHelper.sendEmail(EmailTemplateEnums.VERIFY_CODE_EMAIL_TEMPLATE, "WhimQuest", "邮箱验证码", "15532229961@163.com", map);
    }
}
