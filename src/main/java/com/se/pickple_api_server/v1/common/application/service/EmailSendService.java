package com.se.pickple_api_server.v1.common.application.service;

import com.se.pickple_api_server.v1.common.application.dto.EmailSendDto;

public interface EmailSendService {
    void simpleMailSender(EmailSendDto.Request request);
    //void mailSender(EmailSendDto.Request request);

}
