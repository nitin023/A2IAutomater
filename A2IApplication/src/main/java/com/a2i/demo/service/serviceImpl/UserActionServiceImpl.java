package com.a2i.demo.service.serviceImpl;

import com.a2i.demo.entity.UserAction;
import com.a2i.demo.repository.UserActionRepository;
import com.a2i.demo.service.UserActionService;
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
