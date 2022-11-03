package com.kytc.bikeID.service;

import com.kytc.bikeID.dto.EmailDto;
import com.kytc.bikeID.exeption.AuthenticationCodeNotSendException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderService {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String sendersMail;

    private final SpringTemplateEngine templateEngine;

    public void sendHtmlMessage(EmailDto email) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(email.getProperties());
        helper.setFrom(email.getFrom());
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        String html = templateEngine.process(email.getTemplate(), context);
        helper.setText(html, true);

        log.info("Sending email: {} with html body: {}", email, html);
        emailSender.send(message);
    }

    public void sendAuthenticationCode(String userEmail, String userName, String key) {

        EmailDto email = new EmailDto();
        email.setTo(userEmail);
        email.setFrom(sendersMail);
        String title = "Welcome Email with Key";
        email.setSubject(title);
        email.setTemplate("welcome-email.html");
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", userName);
        properties.put("authenticationCode", key);
        properties.put("subscriptionDate", LocalDate.now().toString());
        email.setProperties(properties);
        try {
            sendHtmlMessage(email);
        } catch (MessagingException e) {
            throw new AuthenticationCodeNotSendException(e);
        }

    }

}
