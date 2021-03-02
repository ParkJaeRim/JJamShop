package com.riim.riim.dao;

import java.util.Optional;

import com.riim.riim.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {

    // User findByKakaoid(int kakaoid);
    // User findByEmail(String email);
    Optional<User> findByEmail(String email);
}
