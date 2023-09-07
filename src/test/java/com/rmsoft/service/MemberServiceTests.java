package com.rmsoft.service;

import com.rmsoft.domain.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author : seolha86
 * @packageName : com.rmsoft.service
 * @fileName : MemberServiceTests
 * @date : 2023-09-05
 * @description :
 * ===========================================================
 * DATE           AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-05        seolha86             최초 생성
 */

@SpringBootTest
@Slf4j
public class MemberServiceTests {
    @Autowired
    private MemberService memberService;

    @DisplayName("회원가입")
    @Test
    void testSignUp() {
        MemberVO vo = new MemberVO();
        vo.setId("test1");
        vo.setPw("1234");
        vo.setName("test1");

        memberService.register(vo);
    }

    @DisplayName("회원 전체 목록 조회")
    @Test
    void testRetrieve() {
        log.warn(memberService.retrieve().toString());
    }

    @DisplayName("로그인")
    @Test
    void testSignIn() {
        MemberVO vo = new MemberVO();
        vo.setId("test1");
        vo.setPw("1234");

        memberService.login(vo.getId(), vo.getPw());
    }
}
