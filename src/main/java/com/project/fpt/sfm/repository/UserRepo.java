package com.project.fpt.sfm.repository;

import com.project.fpt.sfm.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Khắc Vỹ on 10/4/2015.
 */
@Repository
public interface UserRepo extends CrudRepository<User, Integer>{
    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE User SET Password = :password WHERE UserID = :userId")
    int updatePasswordByUserId(@Param("userId") Integer userId, @Param("password") String password);
}
