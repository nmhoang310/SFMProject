package com.project.fpt.sfm.repository;

import com.project.fpt.sfm.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Khắc Vỹ on 10/5/2015.
 */
@Repository
public interface RoleRepo extends CrudRepository<Role, Integer>{
    Role findByRoleName(String roleName);
}
