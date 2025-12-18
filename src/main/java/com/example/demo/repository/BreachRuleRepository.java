package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.BreachRuleEntity;

@Repository
public interface BreachRuleRepository extends JpaRepository<BreachRuleEntity,Long>{
    
}