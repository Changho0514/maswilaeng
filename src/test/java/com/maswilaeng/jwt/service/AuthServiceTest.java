package com.maswilaeng.jwt.service;

import com.maswilaeng.domain.entity.User;
import com.maswilaeng.domain.repository.UserRepository;
import com.maswilaeng.dto.user.request.UserJoinDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AuthServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthService authService;

    @Test
    @Transactional
    void joinTest(){
        //given
        UserJoinDto userJoinDto = new UserJoinDto("test@test.com", "1234", "Gilbert", "1234.jpeg");
        User user = userJoinDto.toEntity();
//        System.out.println("nickName : " + userJoinDto.getNickName());
//        System.out.println("userJoinDto.getEmail() = " + userJoinDto.getEmail());
//        System.out.println("userJoinDto.getPw() = " + userJoinDto.getPw());
//        System.out.println("userJoinDto.getUserImage() = " + userJoinDto.getUserImage());

        //when
        authService.signup(userJoinDto);


        //then
        System.out.println("==============================test 1==================================");
        Assertions.assertThat(userRepository.existsByEmail("test@test.com"));
        System.out.println("==============================test 2==================================");
        System.out.println(userRepository.findByNickName("Gilbert").toString());

    }


}