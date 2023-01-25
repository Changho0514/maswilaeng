package com.maswilaeng.controller;

import com.maswilaeng.Domain.entity.User;
import com.maswilaeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/sign")
    public String createForm(){
        return "users/createUserForm";
    }

    @PostMapping("/sign")
    public String create(UserForm form){

        // 회원가입 API /sign 에서 원하는 정보를 body로 전달하기 위한 form
        User user = new User();

        user.setNickName(form.getNickname());
        user.setEmail(form.getEmail());
        user.setPw(form.getPw());
        user.setPhoneNumber(form.getPhoneNumber());
        user.setUserImage(form.getUserImage());
        user.setIntroduction(form.getUserImage());

        userService.join(user);

        return "redirect:/";
    }

    @GetMapping("/users")
    public String list(Model model){
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "users/userList";
    }
}
