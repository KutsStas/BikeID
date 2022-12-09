//package com.kytc.bikeID.service;
//
//
//import com.kytc.bikeID.dto.EmailDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.mail.MessagingException;
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//
//@SpringBootTest
//public class EmailSenderServiceTest {
//
//    @Autowired
//    private EmailSenderService emailSenderService;
//
//    @Test
//    public void sendHtmlMessageTest() throws MessagingException {
//
//        EmailDto email = new EmailDto();
//        email.setTo("zxcv@gmail.com");
//        email.setFrom("kytc.stas@gmail.com");
//        email.setSubject("Welcome Email");
//        email.setTemplate("welcome-email.html");
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("name", "");
//        properties.put("subscriptionDate", LocalDate.now().toString());
//        email.setProperties(properties);
//
//        assertDoesNotThrow(() -> emailSenderService.sendHtmlMessage(email));
//    }
//
//
//}
//
