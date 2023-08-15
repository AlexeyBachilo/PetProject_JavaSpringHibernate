package com.petproject.repository;

import com.petproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.login IN :login")
    User getUserByLogin (@Param("login") String login);
    @Query("SELECT u FROM User u WHERE u.email IN :email")
    User getUserByEmail (@Param("email") String email);
}
