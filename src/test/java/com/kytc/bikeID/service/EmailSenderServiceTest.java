//package com.kytc.bikeID.service;
//
//import com.kytc.bikeID.entity.Email;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.mail.MessagingException;
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//@SpringBootTest
//public class EmailSenderServiceTest {
//
//    @Autowired
//    private EmailSenderService emailSenderService;
//
//    @Test
//    public void sendHtmlMessageTest() throws MessagingException {
//        Email email = new Email();
//        email.setTo("jjb93474@xcoxc.com");
//        email.setFrom("stas.id@outlook.com");
//        email.setSubject("Welcome Email");
//        email.setTemplate("welcome-email.html");
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("name", "");
//        properties.put("subscriptionDate", LocalDate.now().toString());
//        properties.put("technologies", Arrays.asList("Python", "Go", "C#"));
//        email.setProperties(properties);
//
//        Assertions.assertDoesNotThrow(() -> emailSenderService.sendHtmlMessage(email));
//    }
//
//
//}
//
