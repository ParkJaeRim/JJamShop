package com.riim.riim.dao;

import com.riim.riim.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {

    User findNameByUid(int uid);
}
