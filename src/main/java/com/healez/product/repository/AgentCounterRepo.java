package com.healez.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healez.product.entity.AgentCounter;

@Repository
public interface AgentCounterRepo extends JpaRepository<AgentCounter,Long> {
	
	@Query(value="select max(id) from agent_profile_counter", nativeQuery = true)
	Long getMax();

}
