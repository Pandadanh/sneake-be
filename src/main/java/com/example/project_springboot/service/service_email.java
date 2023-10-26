package com.example.project_springboot.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class service_email  {

    private final JavaMailSender mailSender;

    @Autowired
    public service_email(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String toEmail, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(content, true); // true để cho phép sử dụng HTML trong nội dung email

            mailSender.send(message);
        } catch (MessagingException e) {
            // Xử lý lỗi khi gửi email
            e.printStackTrace();
        }
    }
}
