package com.rmsoft.service;

import com.rmsoft.domain.MemberVO;
import com.rmsoft.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : seolha86
 * @packageName : com.rmsoft.service
 * @fileName : MemberServiceImpl
 * @date : 2023-09-01
 * @description :
 * ===========================================================
 * DATE           AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-01        seolha86             최초 생성
 */
@Service
@AllArgsConstructor
public class MemberService {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private MemberMapper memberMapper;

    // 회원 목록 조회
    public List<MemberVO> retrieve() {
        return memberMapper.retrieve();
    }

    // 회원가입
    public void register(MemberVO vo) {
        vo.setPw(encoder.encode(vo.getPw()));
        memberMapper.register(vo);
    }

    // 로그인 (비밀번호 매치)
    public MemberVO login(String id, String pw) {
        MemberVO vo = memberMapper.findById(id);
        if (vo != null && encoder.matches(pw, vo.getPw())) return vo;
        return null;
    }
}
