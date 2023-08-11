package com.petproject.repository;

import com.petproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.login IN :login")
    User getUserByLogin (@Param("login") String login);
    @Query("SELECT u FROM User u WHERE u.email IN :email")
    User getUserByEmail (@Param("email") String email);
    @Query("UPDATE User u SET u.grantedroleid IN 1 WHERE u.userId IN :userId")
    void makeAdmin(@Param("userId") Long userId);
    @Query("UPDATE User u SET u.grantedroleid IN 2 WHERE u.userId IN :userId")
    void makeModer(@Param("userId") Long userId);
    @Query("UPDATE User u SET u.grantedroleid IN 3 WHERE u.userId IN :userId")
    void makeUser(@Param("userId") Long userId);
}
