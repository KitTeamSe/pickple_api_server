package com.se.pickple_api_server.v1.common.infra.api;

import com.se.pickple_api_server.v1.common.application.dto.EmailSendDto;
import com.se.pickple_api_server.v1.common.application.service.EmailSendService;
import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Api(tags = "이메일 전송 테스트")
public class EmailApiController {

    private final EmailSendService emailSendService;

    @ApiOperation(value ="메일링 서비스")
    @PostMapping(path= "/mail")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse sendMail(@RequestBody EmailSendDto.Request request) {
        System.out.println("메일링 서비스 요청");
        emailSendService.simpleMailSender(request);
        return new SuccessResponse(HttpStatus.OK.value(), "메일 전송 성공");
    }

    @ApiOperation(value ="태깅 메일")
    @PostMapping(path= "/mail/notice")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse sendMail(@RequestBody EmailSendDto.RequestTo request) {
        System.out.println("태깅 메일링 서비스 요청");
        emailSendService.noticeTagging(request);
        return new SuccessResponse(HttpStatus.OK.value(), "메일 전송 성공");
    }

}
