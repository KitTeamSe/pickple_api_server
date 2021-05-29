package com.se.pickple_api_server.v1.common.application.service;

import com.se.pickple_api_server.v1.common.application.dto.EmailSendDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
}
