package com.maswilaeng.config;

import com.maswilaeng.jwt.JwtAccessDeniedHandler;
import com.maswilaeng.jwt.JwtAuthenticationEntryPoint;
import com.maswilaeng.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity // 스프링 시큐리티 사용 어노테이션. SpringSecurityFilterChain이 자동으로 포함됨.
@RequiredArgsConstructor
public class SecurityConfig{

    private final TokenProvider TokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // h2 database로 테스트 할 때 원활히 하려면 관련 API를 모두 무시해주는게 좋음
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring()
                .antMatchers("/h2-console/**", "/favicon.ico");
    }


    /** 정적 리소스나 HTML 문서 등의 보안 예외처리를 위한 configure 메서드**/
    @Bean
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/api/docs/**");
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http

                // token을 사용하는 방식이기에 csrf를 disable 한다.
                .csrf().disable()

                // exception 핸들링에선 직접 만든 클래스 사용하기
                /**401, 403 Exception 핸들링 */
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)


                /**
                 * security가 기본적으로 세션을 사용하는데
                 * 세션 사용하지 않음 -> STATELESS하게 설정
                 * */
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                /** 로그인, 회원가입 API는 토큰이 없는 상태에서 요청이 들어오기에 permitAll 설정 */
                .and()
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .anyRequest().authenticated() //나머지 API는 전부 인증 필요

                /**JwtSecurityConfig 적용 */
                .and()
                .apply(new JwtSecurityConfig(TokenProvider))

                .and().build();
    }
    /**
     * CORS는 리소스를 요청하는 서버의 도메인, 프로토콜 또는 포트가 다를 경우에
     * cross-origin HTTP Request 요청을 실행
     *
     * 보안 상의 이유로 브라우저에서는
     * cross-origin HTTP Request에 대해
     * Same-Origin Policy를 적용. (같은 도메인의 서버인 경우에 정상 동작)
     * 이외의 경우에는, CORS Header 설정을 해주어야 정상적인 요청이 이루어진다.
     */

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("{hostURL:frontEndPort}");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }


}

