package com.healez.product.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.healez.product.data.AgentProfile;
import com.healez.product.entity.AgentCounter;
import com.healez.product.entity.AgentProfileEntity;
import com.healez.product.repository.AgentCounterRepo;
import com.healez.product.response.AgentResponse;
import com.healez.product.service.AgentProfileService;

public class AgentProfileServiceImpl implements AgentProfileService{

	private final static String prefixCode = "HA1";
	
	@Autowired
	AgentCounterRepo repo;
	
	@Override
	public AgentResponse addAgentProfile(AgentProfile agentProfile, AgentResponse agentResponse) {
		
		AgentProfileEntity ape = new AgentProfileEntity();
		ape.setFirstName(agentProfile.getFirstName());
		ape.setLastName(agentProfile.getLastName());
		ape.setGender(agentProfile.getGender());
		ape.setPassportNumber(agentProfile.getPassportNumber());
		ape.setReferralCode(agentProfile.getReferralCode());
	
		//get the Ma count
		Long agentCurrentId = (repo.getMax() !=null  ? repo.getMax()+ 1 :  1)  ;
		ape.setId(prefixCode.concat(String.format("%03d%", agentCurrentId)));

		AgentCounter ageCounter = new AgentCounter();
		ageCounter.setId(agentCurrentId);
		repo.save(ageCounter);
		
		agentResponse.setAgentId(ape.getId());
		agentResponse.setAgentProfile(agentProfile);
		return agentResponse;
	}

}
