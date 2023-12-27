package com.example.redis.service;

import com.example.redis.dto.MemberSaveRequest;
import com.example.redis.entity.Member;
import com.example.redis.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(MemberSaveRequest request) {
        Member member = Member.builder()
                .name(request.getName())
                .password(request.getPassword())
                .build();

        return memberRepository.save(member).getId();
    }
}
