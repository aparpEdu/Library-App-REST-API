package com.example.productstoreapp.email;

import com.example.productstoreapp.exception.ProductStoreAPIException;
import com.example.productstoreapp.utils.ErrorMessages;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Override
    public void send(String receiver, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(receiver);
            helper.setSubject("Email Confirmation");
            helper.setFrom("group_a@gmail.com");
            mailSender.send(mimeMessage);

        }catch (MessagingException exception){
            LOGGER.error("Failed to send Email", exception);
            throw new ProductStoreAPIException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessages.EMAIL_SEND_FAILURE);
        }
    }
}
