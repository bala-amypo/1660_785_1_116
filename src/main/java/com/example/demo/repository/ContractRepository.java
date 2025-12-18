package com.example.demo.repository;

public interface ContractRepository{
    
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.ContractEntity;

@Repository
public interface BreachRuleRepository extends JpaRepository<BreachRuleRepository,Long>{
    
}
}