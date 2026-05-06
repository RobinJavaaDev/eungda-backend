package com.sideproject.eungda.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    @SequenceGenerator(name = "member_seq", sequenceName = "MEMBER_SEQ", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public Member(String email, String nickname, LocalDateTime createdAt) {
        this.email = email;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}