package com.maswilaeng.controller;

import com.maswilaeng.domain.entity.User;
import com.maswilaeng.dto.user.request.UserUpdateRequestDto;
import com.maswilaeng.dto.user.response.UserFindResponseDto;
import com.maswilaeng.dto.user.response.UserInfoResponseDto;
import com.maswilaeng.jwt.service.AuthService;
import com.maswilaeng.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/userInfo")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok().body(userDetails.getUsername());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.getUser(userId));
    }

    @GetMapping("/authTest")
    public ResponseEntity<?> getAuth(Authentication authentication) {
        return ResponseEntity.ok().body(authentication);
    }

    @PutMapping("/user")
    public ResponseEntity<Object> updateUserInfo(
            @RequestBody @Valid UserUpdateRequestDto requestDto) {
        if (requestDto.getNickName() == null) {
            return ResponseEntity.badRequest().build();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.valueOf(authentication.getName());
        userService.updateUser(userId, requestDto);

        return ResponseEntity.ok().build();
    }
//
    @DeleteMapping("/user")
    public ResponseEntity<Object> userWithDraw() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.userWithdraw(Long.valueOf(authentication.getName()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test/user")
    public ResponseEntity<?> testGetUser() {
        log.info("/test/user 요청 들어옴 : accessToken  만료되어도 authentication 존재할까? - >요청전");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("/test/user 요청 들어옴 : accessToken  만료되어도 authentication 존재할까? 요청후 : {}" ,authentication);
        UserFindResponseDto dto = userService.findOne(Long.valueOf(authentication.getName()));
        log.info("/test/user 요청 들어옴 : user : {}", dto.getNickName());
        return ResponseEntity.ok().body(dto);
    }

}
