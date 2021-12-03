package com.healez.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healez.product.entity.AgentProfileEntity;

@Repository
public interface AgentProfileRepo extends JpaRepository<AgentProfileEntity,String> {
	

}
