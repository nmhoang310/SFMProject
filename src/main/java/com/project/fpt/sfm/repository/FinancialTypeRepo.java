package com.project.fpt.sfm.repository;

import com.project.fpt.sfm.entities.FinancialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Khắc Vỹ on 10/12/2015.
 */
@Repository
public interface FinancialTypeRepo extends JpaRepository<FinancialType, Integer>{
    FinancialType findByFinancialTypeName(String financialTypeName);
    FinancialType findByFinancialTypeNameAndFinancialRate(String financialTypeName, int financialRate);
}
