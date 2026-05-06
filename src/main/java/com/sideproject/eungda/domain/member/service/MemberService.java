package com.sideproject.eungda.domain.member.service;

import com.sideproject.eungda.domain.member.dto.MemberRequestDto;
import com.sideproject.eungda.domain.member.dto.MemberResponseDto;
import com.sideproject.eungda.domain.member.entity.Member;
import com.sideproject.eungda.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto createMember(MemberRequestDto requestDto) {
        if (memberRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        Member member = Member.builder()
                .email(requestDto.getEmail())
                .nickname(requestDto.getNickname())
                .createdAt(LocalDateTime.now())
                .build();

        return MemberResponseDto.from(memberRepository.save(member));
    }

    public MemberResponseDto getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        return MemberResponseDto.from(member);
    }

    @Transactional
    public MemberResponseDto updateMember(Long id, MemberRequestDto requestDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        member.updateNickname(requestDto.getNickname());
        return MemberResponseDto.from(member);
    }
}