package com.example.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.survey.entity.User;
@Repository
public interface UserDao extends JpaRepository<User, String>{

}
