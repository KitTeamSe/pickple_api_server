package com.se.pickple_api_server.v1.common.domain.type;

import lombok.Getter;

@Getter
public enum EmailTextType {

    TAGGING_NOTICE("방금 회원님이 관심있을 법한 모집글이 올라왔어요! 한 번 확인해 보세요. 😘");

    private final String text;

    EmailTextType(final String text) {
        this.text = text;
    }
}
