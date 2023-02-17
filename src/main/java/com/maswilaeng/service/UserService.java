package com.maswilaeng.service;

import com.maswilaeng.Domain.entity.User;
import com.maswilaeng.Domain.repository.UserRepository;
import com.maswilaeng.dto.user.request.UserUpdateRequestDto;
import com.maswilaeng.dto.user.response.UserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user){
        // 같은 닉네임의 중복 회원 X
        User result = userRepository.findByNickName(user.getNickName());

        if(result != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        userRepository.save(user);
        return user.getId();
    }



    public List<User> findUsers(){

        return userRepository.findAll();
    }

    public Optional<User> findOne(Long userId){
        return userRepository.findById(userId);
    }


    public void deleteByUserId(Long id) {
        userRepository.deleteById(id);
    }




    public UserInfoResponseDto getUser(Long userId){
        User user = userRepository.findById(userId).get();
        return UserInfoResponseDto.of(user);
    }

    public void updateUser(Long userId, UserUpdateRequestDto requestDto) {
        User selectedUser = userRepository.findById(userId).get();

        selectedUser.update(requestDto); //더티체킹
    }
    public void userWithdraw(Long userId) {
        User user = userRepository.findById(userId).get();
        user.withdraw();

    }
}
