package com.maswilaeng.Domain.repository;

import com.maswilaeng.Domain.entity.Post;
import com.maswilaeng.Domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByNickName(String nickName);

    Optional<User> findByEmail(String email);
}
