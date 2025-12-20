package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.VendorTierEntity;

@Repository
public interface VendorTierRepository extends JpaRepository<VendorTierEntity,Long>{
    
}

