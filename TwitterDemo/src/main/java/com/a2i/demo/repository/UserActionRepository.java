package com.a2i.demo.repository;

import com.a2i.demo.entity.UserAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionRepository extends JpaRepository<UserAction,Integer> {

}
