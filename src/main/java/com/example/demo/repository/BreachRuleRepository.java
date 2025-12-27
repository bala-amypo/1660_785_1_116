// package com.example.demo.repository;

// import com.example.demo.entity.BreachRule;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.Optional;

// public interface BreachRuleRepository extends JpaRepository<BreachRule, Long> {
//     Optional<BreachRule> findFirstByActiveTrueOrderByIsDefaultRuleDesc();
// }

package com.example.demo.repository;

import com.example.demo.entity.BreachRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BreachRuleRepository extends JpaRepository<BreachRule, Long> {
    Optional<BreachRule> findFirstByActiveTrueOrderByIsDefaultRuleDesc();
}