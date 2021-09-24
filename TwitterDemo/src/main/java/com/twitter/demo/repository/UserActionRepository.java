package com.twitter.demo.repository;

import com.twitter.demo.model.UserAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionRepository extends JpaRepository<UserAction,Integer> {

}
