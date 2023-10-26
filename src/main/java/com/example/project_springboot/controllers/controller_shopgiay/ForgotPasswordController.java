package com.example.project_springboot.controllers.controller_shopgiay;

import com.example.project_springboot.model.ResponseObject;
import com.example.project_springboot.model.tbl_users;
import com.example.project_springboot.repositories.userRepositories;
import com.example.project_springboot.service.service_email;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Forgot-password")
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private service_email emailService;

    @Autowired
    private userRepositories userRepository;

    @PostMapping("/forgot_test")
    public ResponseEntity<?> sendPasswordResetEmail(@RequestBody Map<String, String> requestData) {
        String toEmail = requestData.get("email");
        String resetLink = requestData.get("link");

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("leducduytan2003@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject("Xác nhận đổi mật khẩu");
            String content = "<p>Chào bạn,</p>";
            content += "<p>Để đổi mật khẩu, hãy nhấp vào đường dẫn sau:</p>";
            content += "<p><a href='" + resetLink + "'>" + resetLink + "</a></p>";
            content += "<p>Cảm ơn bạn!</p>";
            helper.setText(content, true);
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi khi gửi email
        }


        if (1==1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully inserted")
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            new ResponseObject("Fail", "No products found")
        );
    }


    @PostMapping("/forgot")
    public ResponseEntity<Map<String, Object>> forgotPassword(@RequestBody Map<String, String> requestData) {
        String email = requestData.get("email");
        Map<String, Object> response = new HashMap<>();

        if (email == null || email.isEmpty()) {
            response.put("error", "Không được để trống email");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (!isValidEmail(email)) {
            response.put("error", "Email không đúng định dạng");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        tbl_users user = userRepository.findByEmail(email);

        if (user == null) {
            response.put("error", "Email không tồn tại trên hệ thống");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        String resetToken = generateResetToken(email);
        String resetLink = "http://localhost/DOANWED/shop-giay/index.php?page=reset_pass&reset_token=" + resetToken;

        user.setResetToken(resetToken);
        userRepository.save(user);

        String emailContent = "<p>Chào bạn</p>" +
            "<p>Vui lòng click vào đây để khôi phục mật khẩu: " + resetLink + "</p>" +
            "<p>Nếu không phải bạn, vui lòng bỏ qua email này</p>";

        boolean emailSent = sendEmail(email, "Khôi phục mật khẩu", emailContent);

        if (emailSent) {
            response.put("is_send", 1);
            response.put("message", "Gửi yêu cầu thành công, vui lòng kiểm tra email của bạn!!!");
        } else {
            response.put("is_send", 0);
            response.put("message", "Email chưa được kích hoạt, vui lòng kiểm tra email để kích hoạt tài khoản!!!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private boolean isValidEmail(String email) {
        // Điều kiện kiểm tra email hợp lệ ở đây
        return true;
    }

    private String generateResetToken(String email) {
        // Đây là ví dụ tạo mã reset token, bạn có thể thay đổi cách tạo mã của mình
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(email.getBytes());
            return UUID.nameUUIDFromBytes(messageDigest).toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean sendEmail(String toEmail, String subject, String content) {
        try {

            emailService.sendEmail(toEmail, subject, content);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
