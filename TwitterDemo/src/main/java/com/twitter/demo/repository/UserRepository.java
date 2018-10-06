package com.twitter.demo.repository;

import com.twitter.demo.modal.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
