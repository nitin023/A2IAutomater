package com.twitter.demo.Services;

import com.twitter.demo.modal.User;
import com.twitter.demo.repository.IUserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

private final IUserRepository userRepository ;


public UserService(IUserRepository userRepository)
{
    this.userRepository = userRepository;
}
    public boolean saveUser(User user)
    {
        boolean isUserSaved = false;
        userRepository.save(user);
        return isUserSaved;
    }
}
