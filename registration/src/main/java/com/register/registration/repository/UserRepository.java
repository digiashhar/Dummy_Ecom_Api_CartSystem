package com.register.registration.repository;

import com.register.registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findUserByusername(String name);

    //SIGN-IN:
    User findByMobileAndPassword(long mobile, String password);
}
