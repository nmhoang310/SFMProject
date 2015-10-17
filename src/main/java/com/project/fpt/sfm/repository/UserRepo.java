package com.project.fpt.sfm.repository;

import com.project.fpt.sfm.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Khắc Vỹ on 10/4/2015.
 */
@Repository
public interface UserRepo extends CrudRepository<User, Integer>{
    User findByUsername(String username);
}
