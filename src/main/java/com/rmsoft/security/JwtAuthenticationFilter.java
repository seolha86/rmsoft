package com.rmsoft.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : seolha86
 * @packageName : com.rmsoft.security
 * @fileName : JwtAuthenticationFilter
 * @date : 2023-09-05
 * @description :
 * ===========================================================
 * DATE           AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-05        seolha86             최초 생성
 */

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = parseBearerToken(request);

            if (token != null && !token.equalsIgnoreCase("null")) {
                String memberId = tokenProvider.validate(token);
                log.info("Authenticated userId : {}" , memberId);

                AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, null,
                        AuthorityUtils.NO_AUTHORITIES);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext(); // context 생성
                securityContext.setAuthentication(authenticationToken); // token 제작
                SecurityContextHolder.setContext(securityContext);
            }
        } catch (Exception e) {
            logger.error("Cannot store authenticated user information in security context.", e);
        }
        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // Bearer: .... / 난수형태의 토큰값
        log.info("bearerToken : {}", bearerToken);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // 토큰이 있으면 리턴
            String token = bearerToken.substring(7);
            log.info("read token {}", token);
            return token;
        }
        return null;
    }
}
