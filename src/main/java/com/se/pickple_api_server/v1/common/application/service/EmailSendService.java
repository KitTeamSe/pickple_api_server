package com.se.pickple_api_server.v1.common.application.service;

import com.se.pickple_api_server.v1.common.application.dto.EmailSendDto;

import java.util.List;

public interface EmailSendService {
    void simpleMailSender(EmailSendDto.Request request);
    void noticeTagging(EmailSendDto.RequestTo request);
    void sendEmailToUser(String email);
    void sendEmailToUser(List<String> email);
}
