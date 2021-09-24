package com.twitter.demo.Services;

import com.twitter.demo.DTO.UserActionRepository;
import com.twitter.demo.modal.UserAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActionServiceImpl implements UserActionService{
    @Autowired
    UserActionRepository userActionRepository;
    @Override
    public void insertUserAction(UserAction userAction) {
        userActionRepository.save(userAction);
    }

}
