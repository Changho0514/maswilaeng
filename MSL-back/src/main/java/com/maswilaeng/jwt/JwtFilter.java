package com.maswilaeng.jwt;

import com.maswilaeng.config.CustomUserDetailsService;
import com.maswilaeng.jwt.entity.JwtTokenProvider;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * filter를 통해 JWT토큰이 유효한지 검증하는 메서드
 */
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;


        /**
         * 실제 필터링 로직이라고 볼 수 있음
         * JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext에 저장하는 역할 수행
         */
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

            Cookie[] cookies = request.getCookies();
            String accessToken = " ";

            log.info("쿠키에서 토큰 꺼냄 : {}", cookies);

            if (!ObjectUtils.isEmpty(cookies)) {
                log.info("쿠키 값 있음 -> 로그인 상태");

                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("ACCESS_TOKEN")) {
                        accessToken = cookie.getValue();
                        if (StringUtils.hasText(accessToken) && jwtTokenProvider.validateToken(accessToken)) {
                            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            log.info(" 'doFilterInternal 작동' -> Security Context에 '{}'인증 정보를 저장했습니다.", authentication.getName());
                            log.info("doFilterInternal 이후 Security Context : {}", SecurityContextHolder.getContext());
                            filterChain.doFilter(request, response);
                        }
                    }

                }
            } else {
                log.info("{} 에 대한 처리 건너뛰기");
                filterChain.doFilter(request, response);
            }
        }


    /** 재발급 이슈 고치고 있는 코드
    /*
     * 실제 필터링 로직이라고 볼 수 있음
     * JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext에 저장하는 역할 수행
     *
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        Cookie[] cookies = request.getCookies();
        String accessToken = " ";
        String refreshToken = " ";

        log.info("쿠키에서 토큰 꺼냄 : {}", cookies);

        if (!ObjectUtils.isEmpty(cookies)) {
            log.info("쿠키 값 있음 -> 로그인 상태");

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ACCESS_TOKEN")) {
                    accessToken = cookie.getValue();
                } else if (cookie.getName().equals("REFRESH_TOKEN")) {
                    refreshToken = cookie.getValue();
                }

            }

            //만료되지 않은 상태
            if (StringUtils.hasText(accessToken) && jwtTokenProvider.validateToken(accessToken)) {

                log.info("AccessToken이 유효합니다.");
                Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("doFilterInternal 작동 -> Security Context에 '{}'인증 정보를 저장했습니다.", authentication.getName());
                log.info("doFilterInternal 이후 Security Context : {}", SecurityContextHolder.getContext());
                filterChain.doFilter(request, response);
            }

            // 엑세스 토큰은 만료, 리프레시 토큰은 존재
            else if (!jwtTokenProvider.validateToken(accessToken) && refreshToken != null) {
                jwtTokenProvider.validateToken(refreshToken);
            }
            filterChain.doFilter(request, response);
        }
    }
    */

    /**
     * HTTP Request 헤더에서 토큰의 정보만 추출
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        log.info("JwtFilter의 resolve 실행중 StringUtils.hasText(bearerToken): {}", StringUtils.hasText(bearerToken));
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
