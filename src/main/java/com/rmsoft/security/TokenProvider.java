package com.rmsoft.security;

import com.rmsoft.domain.MemberVO;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author : seolha86
 * @packageName : com.rmsoft.security
 * @fileName : TokenProvider
 * @date : 2023-09-05
 * @description :
 * ===========================================================
 * DATE           AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-05        seolha86             최초 생성
 */

@Service
public class TokenProvider {
    private static final String SECRET_KEY = "RMSOFT";

    // 토큰 생성
    public String create(MemberVO memberVO) {
        // 기한
        Date expriyDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
        return Jwts
                .builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setSubject(memberVO.getId())
                .setIssuer("Wa")
                .setIssuedAt(new Date())
                .setExpiration(expriyDate)
                .compact();
    }

    // 토큰 검증
    public String validate(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
