package com.education.mail;


import com.education.model.entity.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class NotificationService {

    public JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(UserModel userModel) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String link = "<a href=\"http://localhost:8080/api/activate?key=\">CLICK HERE TO ACTIVATE YOUR ACCOUNT</a>";
        helper.setTo(userModel.getEmail());
        helper.setText("Your Activation Code Is : " + userModel.getActivationKey() + "<br>" + link, true);
        helper.setSubject("HELLO " + userModel.getUsername());

        javaMailSender.send(message);
    }
}