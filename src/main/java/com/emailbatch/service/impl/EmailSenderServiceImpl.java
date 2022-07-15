package com.emailbatch.service.impl;

import com.emailbatch.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import static com.emailbatch.util.Constants.EMAIL_SENDER;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendActionEmail(String to, String subjects, String text) {
        final MimeMessagePreparator mailMessage = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setFrom(EMAIL_SENDER, EMAIL_SENDER);
            message.setTo(to);
            message.setSubject(subjects);
            message.setText(text);
        };

        javaMailSender.send(mailMessage);
    }
}
