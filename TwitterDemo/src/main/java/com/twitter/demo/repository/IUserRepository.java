package com.twitter.demo.repository;

import com.twitter.demo.modal.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User,Integer> {

}
