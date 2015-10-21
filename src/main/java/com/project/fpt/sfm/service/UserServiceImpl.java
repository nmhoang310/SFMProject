package com.project.fpt.sfm.service;

import com.project.fpt.sfm.entities.User;
import com.project.fpt.sfm.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Hoang on 10/21/2015.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public int updatePasswordByUserId(Integer userId, String password) {
        return userRepo.updatePasswordByUserId(userId, password);
    }
}
