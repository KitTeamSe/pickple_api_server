package com.se.pickple_api_server.v1.common.application.service;

import com.se.pickple_api_server.v1.common.application.dto.EmailSendDto;
import com.se.pickple_api_server.v1.common.domain.type.EmailSubjectType;
import com.se.pickple_api_server.v1.common.domain.type.EmailTextType;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailSendServiceImpl implements EmailSendService {

    private final JavaMailSender javaMailSender;

    @Override
    public void simpleMailSender(EmailSendDto.Request request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getText());
        javaMailSender.send(message);
    }

    @Override
    public void noticeTagging(EmailSendDto.RequestTo request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getTo());
        message.setSubject(EmailSubjectType.TAGGING_NOTICE.getSubject());
        message.setText(EmailTextType.TAGGING_NOTICE.getText());
        javaMailSender.send(message);
    }

    @Override
    public void sendEmailToUser(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(EmailSubjectType.TAGGING_NOTICE.getSubject());
        message.setText(EmailTextType.TAGGING_NOTICE.getText());
        javaMailSender.send(message);
    }

    @Override
    public void sendEmailToUser(List<String> emailList) {
        for (String email : emailList) {
            sendEmailToUser(email);
        }
    }

    public void setMessage(String to, String subject, String text) {

    }

}
