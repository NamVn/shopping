package com.namvn.shopping.persistence.repository;

import com.namvn.shopping.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Override
    void delete(User user);
}

