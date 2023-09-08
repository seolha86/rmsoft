package com.rmsoft.controller;

import com.rmsoft.domain.MemberVO;
import com.rmsoft.domain.ResponseDTO;
import com.rmsoft.security.TokenProvider;
import com.rmsoft.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : seolha86
 * @packageName : com.rmsoft.controller
 * @fileName : MemberController
 * @date : 2023-09-01
 * @description :
 * ===========================================================
 * DATE           AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-01        seolha86             최초 생성
 */

@RestController
@RequestMapping("/member/")
@AllArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody MemberVO memberVO) {
        try {
            if (memberVO == null) {
                throw new RuntimeException("Invalid value.");
            }

            memberService.register(memberVO);

            return ResponseEntity.ok().body(memberVO);
        } catch (Exception e) {
            ResponseDTO<?> responseDTO = ResponseDTO.builder().error(e.getLocalizedMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("signin")
    public ResponseEntity<?> signin(@RequestBody MemberVO memberVO) {
        MemberVO vo = memberService.login(memberVO.getId(), memberVO.getPw());

        if (vo != null) {
            final String token = tokenProvider.create(vo);
            log.info("토큰 : {}", token);
            return ResponseEntity.ok().body(token);
        } else {
            ResponseDTO<?> responseDTO = ResponseDTO.builder().error("Login failed").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
