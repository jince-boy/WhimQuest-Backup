package com.whim.common.utils;

import com.whim.common.core.enums.EmailTemplateEnums;
import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Jince
 * @since: 2023.12.02 下午 11:23
 * @description: 邮箱工具类
 */
@Component
@Slf4j
public class EmailHelper {
    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    /**
     * 发送邮件
     *
     * @param templateName 模板名称
     * @param senderName   发送人姓名
     * @param subject      主题
     * @param to           收件人地址
     * @param variables    变量
     */
    public void sendEmail(EmailTemplateEnums templateName, String senderName, String subject, String to, Map<String, String> variables) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.toString());
            // 发件人地址，发件人姓名
            helper.setFrom(emailFrom, senderName);
            // 收件人
            helper.setTo(to);
            // 主题
            helper.setSubject(subject);
            ClassPathResource resource = new ClassPathResource("templates/email/" + templateName.getName() + ".html");
            String templateContent = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            if (! variables.isEmpty()) {
                templateContent = processTemplate(templateContent, variables);
            }
            helper.setText(templateContent, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    /**
     * 加工模板信息
     *
     * @param template  模板
     * @param variables 变量
     * @return 加工好的模板
     */
    public String processTemplate(String template, Map<String, String> variables) {
        for (Map.Entry<String, String> stringStringEntry : variables.entrySet()) {
            String variable = "{%s}".formatted(stringStringEntry.getKey());
            String value = stringStringEntry.getValue();
            template = template.replaceAll(Pattern.quote(variable), value);
        }
        return template;
    }
}
