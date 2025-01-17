package com.thebinh.identity_service.repository;

import com.thebinh.identity_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// để JpaRepository<User, Long> là vì kiểu dữ liệu của Id là Long
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
    User save(User user);
    void deleteById(long id);
    User findById(long id);
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
