package com.se.pickple_api_server.v1.common.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EmailSendDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Request {
        private String to;
        private String subject;
        private String text;
    }
}
