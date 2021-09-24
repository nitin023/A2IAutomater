package com.twitter.demo.service.serviceImpl;

import com.twitter.demo.repository.UserActionRepository;
import com.twitter.demo.entity.UserAction;
import com.twitter.demo.service.UserActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActionServiceImpl implements UserActionService {
    @Autowired
    UserActionRepository userActionRepository;
    @Override
    public void insertUserAction(UserAction userAction) {
        userActionRepository.save(userAction);
    }

}
