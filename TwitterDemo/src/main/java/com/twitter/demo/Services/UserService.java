package com.twitter.demo.Services;

import com.twitter.demo.modal.User;
import com.twitter.demo.repository.UserProfileRepository;
import com.twitter.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {


    @Autowired
    private final UserRepository userRepository;


    @Autowired
    private UserProfileRepository userProfileRepository;

public UserService(UserRepository userRepository)
{
    this.userRepository = userRepository;
}

    public void saveUser(User user)
    {
        userRepository.save(user);
    }
}
