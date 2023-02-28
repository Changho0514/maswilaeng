package com.maswilaeng.config;

import com.maswilaeng.jwt.RSaEncryption;
import com.maswilaeng.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringSecurityConfig{

    private final TokenProvider tokenProvider;
    private final RSaEncryption rsAencryption;


    // h2 database로 테스트 할 때 원활히 하려면 관련 API를 모두 무시해주는게 좋음
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return (web) -> web.ignoring()
//                .antMatchers("/h2-console/**", "/favicon.ico");
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//
//                // token을 사용하는 방식이기에 csrf를 disable 한다.
//                .csrf().disable()
//
//                // exception 핸들링에선 직접 만든 클래스 사용하기
//                /**401, 403 Exception 핸들링 */
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .accessDeniedHandler(jwtAccessDeniedHandler)
//
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin()
//
//                /**
//                 * security가 기본적으로 세션을 사용하는데
//                 * 세션 사용하지 않음 -> STATELESS하게 설정
//                 * */
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                /** 로그인, 회원가입 API는 토큰이 없는 상태에서 요청이 들어오기에 permitAll 설정 */
//                .and()
//                .authorizeRequests()
//                .antMatchers("/auth/**").permitAll()
//                .anyRequest().authenticated() //나머지 API는 전부 인증 필요
//
//                /**JwtSecurityConfig 적용 */
//                .and()
//                .apply(new JwtSecurityConfig(tokenProvider))
//
//                .and().build();
//    }
    /**
     * CORS는 리소스를 요청하는 서버의 도메인, 프로토콜 또는 포트가 다를 경우에
     * cross-origin HTTP Request 요청을 실행
     *
     * 보안 상의 이유로 브라우저에서는
     * cross-origin HTTP Request에 대해
     * Same-Origin Policy를 적용. (같은 도메인의 서버인 경우에 정상 동작)
     * 이외의 경우에는, CORS Header 설정을 해주어야 정상적인 요청이 이루어진다.
     */

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.addAllowedOrigin("{hostURL:frontEndPort}");
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedMethod("*");
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/api/**", configuration);
//        return source;
//    }


}

