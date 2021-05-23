package com.se.pickple_api_server.v1.profile.infra.api;


import com.se.pickple_api_server.v1.common.application.dto.SearchDto;
import com.se.pickple_api_server.v1.common.infra.dto.PageRequest;
import com.se.pickple_api_server.v1.common.infra.dto.SuccessResponse;
import com.se.pickple_api_server.v1.profile.application.dto.ProfileCreateDto;
import com.se.pickple_api_server.v1.profile.application.dto.ProfileReadDto;
import com.se.pickple_api_server.v1.profile.application.dto.ProfileUpdateDto;
import com.se.pickple_api_server.v1.profile.application.service.ProfileCreateService;
import com.se.pickple_api_server.v1.profile.application.service.ProfileReadService;
import com.se.pickple_api_server.v1.profile.application.service.ProfileUpdateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Api(tags = "프로필 관리")
public class ProfileApiController {

    private final ProfileCreateService profileCreateService;
    private final ProfileReadService profileReadService;
    private final ProfileUpdateService profileUpdateService;

    @ApiOperation(value = "프로필 등록")
    @PostMapping(path = "/profile")
    @PreAuthorize("hasAnyAuthority('MEMBER')")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SuccessResponse<Long> create(@RequestBody @Validated ProfileCreateDto.Request request) {
        return new SuccessResponse(HttpStatus.CREATED.value(), "프로필 등록 성공", profileCreateService.create(request));
    }

    @ApiOperation(value = "내 프로필 조회")
    @GetMapping(path = "/profile/my")
    @PreAuthorize("hasAnyAuthority('MEMBER')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<ProfileReadDto.Response> readMyProfile() {
        return new SuccessResponse(HttpStatus.OK.value(), "내 프로필 조회 성공", profileReadService.readMyProfile());
    }

    @ApiOperation(value = "프로필 상세조회")
    @GetMapping(path = "/profile/{profileId}")
    @PreAuthorize("hasAnyAuthority('MEMBER','ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<ProfileReadDto.Response> readProfileDetail(@PathVariable(name = "profileId") Long profileId) {
        return new SuccessResponse(HttpStatus.OK.value(), "프로필 상세조회 성공", profileReadService.readById(profileId));
    }

    // 프로필 전체조회
    @ApiOperation(value = "프로필 목록조회 페이징")
    @GetMapping(path = "/profile")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<Pageable> readAllProfile(@Validated PageRequest pageRequest) {
        return new SuccessResponse(HttpStatus.OK.value(), "프로필 목록 조회 페이징 성공", profileReadService.readAll(pageRequest.of()));
    }

    // 프로필 수정
    @ApiOperation(value = "프로필 상세 내용 수정")
    @PutMapping(path = "/profile")
    @PreAuthorize("hasAnyAuthority('MEMBER')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<Long> update(@RequestBody @Validated ProfileUpdateDto.Request request) {
        return new SuccessResponse(HttpStatus.OK.value(), "프로필 수정 성공", profileUpdateService.update(request));
    }

    // 프로필 공개/비공개
    @ApiOperation(value = "프로필 공개/비공개")
    @PutMapping(path = "/profile/visibility")
    @PreAuthorize("hasAnyAuthority('MEMBER')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse updateIsOpen(@RequestBody @Validated ProfileUpdateDto.IsOpenRequest request) {
        profileUpdateService.updateIsOpen(request);
        return new SuccessResponse(HttpStatus.OK.value(), "프로필 수정 성공");
    }

    @ApiOperation(value = "[관리자] 프로필 검색")
    @GetMapping(path = "/profile/search")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public SuccessResponse<Pageable> readSearchProfile(@Validated SearchDto.Request pageRequest) {
        return new SuccessResponse(HttpStatus.OK.value(), "회원 검색 성공", profileReadService.search(pageRequest));
    }
}
