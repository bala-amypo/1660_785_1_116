package com.example.demo.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.ContractEntity;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity,Long>{
    
}