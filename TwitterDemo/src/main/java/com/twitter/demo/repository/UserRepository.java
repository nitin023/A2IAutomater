package com.twitter.demo.repository;

import com.twitter.demo.DTO.UserDTO;
import com.twitter.demo.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

     List<User> findAll();

     User findByUserId(Long userId);

}
