package com.sideproject.eungda.domain.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sideproject.eungda.domain.member.dto.MemberRequestDto;
import com.sideproject.eungda.domain.member.dto.MemberResponseDto;
import com.sideproject.eungda.domain.member.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "회원 API", description = "회원 가입, 조회, 수정 기능을 제공합니다.")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @Operation(summary = "회원 가입", description = "새로운 회원을 등록합니다.")
    public ResponseEntity<MemberResponseDto> createMember(@Valid @RequestBody MemberRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.createMember(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "회원 단건 조회", description = "회원 ID로 특정 회원의 정보를 조회합니다.")
    public ResponseEntity<MemberResponseDto> getMember(
            @Parameter(description = "조회할 회원의 고유 ID", example = "1")
            @PathVariable Long id) {
        MemberResponseDto responseDto = memberService.getMember(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "회원 정보 수정", description = "회원 ID로 특정 회원의 닉네임 등을 수정합니다.")
    public ResponseEntity<MemberResponseDto> updateMember(
            @Parameter(description = "수정할 회원의 고유 ID", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody MemberRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.updateMember(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }
}