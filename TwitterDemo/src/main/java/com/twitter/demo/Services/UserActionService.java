package com.twitter.demo.Services;

import com.twitter.demo.modal.UserAction;
import org.springframework.stereotype.Service;


public interface UserActionService {
     void insertUserAction(UserAction userAction);
}
