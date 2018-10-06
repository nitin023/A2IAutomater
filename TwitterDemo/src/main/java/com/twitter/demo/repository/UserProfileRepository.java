package com.twitter.demo.repository;

import com.twitter.demo.modal.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile,Long> {
}
