package com.emailbatch.batch;

import com.emailbatch.model.dto.EmailDTO;
import com.emailbatch.service.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.SendFailedException;

import static com.emailbatch.util.Constants.ACTION_EMAIL_SUBJECT;
import static com.emailbatch.util.Constants.ACTION_TEXT;

@Slf4j
public class EmailItemProcessor implements ItemProcessor<EmailDTO, EmailDTO> {
    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public EmailDTO process(EmailDTO emailDTO) throws Exception {
        log.debug("Sen mail to: {}", emailDTO.getEmail());
        emailSenderService.sendActionEmail(emailDTO.getEmail(), ACTION_EMAIL_SUBJECT, ACTION_TEXT);

//        try {
//            emailSenderService.sendActionEmail(emailDTO.getEmail(), ACTION_EMAIL_SUBJECT, ACTION_TEXT);
//            emailDTO.setEmailSent(Boolean.TRUE);
//        } catch (SendFailedException exception) {
//            log.debug(exception.getMessage());
//        }

        return emailDTO;
    }
}
