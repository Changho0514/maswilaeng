package com.maswilaeng.service;

import com.maswilaeng.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


class UserServiceTest {

    @Autowired
    UserService userService;
    UserRepository userRepository;
}