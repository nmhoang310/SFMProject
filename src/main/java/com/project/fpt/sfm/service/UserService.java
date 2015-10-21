package com.project.fpt.sfm.service;

import com.project.fpt.sfm.entities.User;

/**
 * Created by Hoang on 10/21/2015.
 */
public interface UserService {
    User findByUsername(String username);

    int updatePasswordByUserId(Integer userId, String password);
}
