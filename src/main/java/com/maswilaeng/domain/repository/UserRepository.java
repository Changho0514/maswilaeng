package com.maswilaeng.domain.repository;

import com.maswilaeng.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    User save(User user);
    User findByNickName(String nickName);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
