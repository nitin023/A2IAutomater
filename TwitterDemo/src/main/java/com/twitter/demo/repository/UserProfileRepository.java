package com.twitter.demo.repository;

import com.twitter.demo.modal.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
}
