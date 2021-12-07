package com.healez.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healez.product.entity.AgentProfileEntity;

@Repository
public interface AgentProfileRepo extends JpaRepository<AgentProfileEntity,String> {
	
	@Query(value="select * from agent_profile where agent_id=?1", nativeQuery = true)
	AgentProfileEntity verifyAgent(String agentId);


}
