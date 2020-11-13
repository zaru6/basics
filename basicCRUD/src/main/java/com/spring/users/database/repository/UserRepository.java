package com.spring.users.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.users.database.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
