package com.project.fpt.sfm.repository;

import com.project.fpt.sfm.entities.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
@Repository
public interface ClassRepo extends JpaRepository<Clazz, Integer>{
    Clazz findByClassName(String className);
}
