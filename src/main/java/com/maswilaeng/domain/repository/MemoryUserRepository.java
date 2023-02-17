//package com.maswilaeng.Domain.repository;
//
//import com.maswilaeng.Domain.entity.User;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Repository
//public class MemoryUserRepository implements UserRepository{
//
//    private static Map<Long, User> store = new ConcurrentHashMap<>();
//    private static long sequence = 0L;
//
//    @Override
//    public User save(User user) {
//        user.setUser_id(++sequence);
//        store.put(user.getUser_id(), user);
//        return user;
//    }
//
//    @Override
//    public Optional<User> findById(Long id) {
//        return Optional.ofNullable(store.get(id));
//    }
//
//    @Override
//    public Optional<User> findByNickName(String name) {
//        return store.values().stream()
//                .filter(user -> user.getNickName().equals(name))
//                .findAny();
//    }
//
//    @Override
//    public List<User> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//    // 테스트를 위한 메소드
//    public void clearStore(){
//        store.clear();
//    }
//}
