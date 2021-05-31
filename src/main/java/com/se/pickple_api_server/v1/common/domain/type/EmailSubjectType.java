package com.se.pickple_api_server.v1.common.domain.type;

import lombok.Getter;

@Getter
public enum EmailSubjectType {

    TAGGING_NOTICE("[픽플] 태깅 알림");

    private final String subject;

    EmailSubjectType(final String subject) {
        this.subject = subject;
    }
}
