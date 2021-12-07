package com.healez.product.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.healez.product.data.AgentProfile;
import com.healez.product.data.VerifyAgent;
import com.healez.product.entity.AgentCounter;
import com.healez.product.entity.AgentProfileEntity;
import com.healez.product.entity.UserDetailEntity;
import com.healez.product.repository.AgentCounterRepo;
import com.healez.product.repository.AgentProfileRepo;
import com.healez.product.repository.UserDetailRepo;
import com.healez.product.response.AgentResponse;
import com.healez.product.response.VerifyAgentResponse;
import com.healez.product.service.AgentProfileService;

public class AgentProfileServiceImpl implements AgentProfileService{

	private final static String prefixCode = "HA1";
	
	@Autowired
	AgentCounterRepo repo;

	@Autowired
	AgentProfileRepo agentRepo;
	
	@Autowired
	UserDetailRepo userRepo;
	
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

	@Override
	public VerifyAgentResponse verifyAgent(String agentId) {
		
		VerifyAgentResponse agentResponse = new VerifyAgentResponse();
		
		if(agentId!=null && !agentId.isEmpty()) {
			AgentProfileEntity agentProfileEntity = agentRepo.verifyAgent(agentId);
			
			if(agentProfileEntity!= null) {
				 Optional<UserDetailEntity> userInfo = userRepo.findById(agentId);
				 if(userInfo!=null && !userInfo.isEmpty()) {
					 if(userInfo.get() !=null && userInfo.get().getAgentStatus().equalsIgnoreCase("A") ) {
						 
						 VerifyAgent agentInformation = new VerifyAgent();
						 
						 AgentProfile agentProfile = new AgentProfile();
						 agentProfile.setEmail(agentProfileEntity.getEmail());
						 agentProfile.setFirstName(agentProfileEntity.getFirstName());
						 agentProfile.setGender(agentProfileEntity.getGender());
						 agentProfile.setLastName(agentProfileEntity.getLastName());
						 agentProfile.setPassportNumber(agentProfileEntity.getPassportNumber());
						 agentProfile.setReferralCode(agentProfile.getReferralCode());
						 
						 agentInformation.setAgentProfile(agentProfile);
						 agentInformation.setStatus(userInfo.get().getAgentStatus());
						 
						 
						 agentResponse.setAgentInformation(agentInformation);
						 agentResponse.setResponseCode("200");
						 agentResponse.setResponseMessage("Succesfully Retrieved the Information");
						 
				 }else {
							agentResponse.setResponseCode("300");
							agentResponse.setResponseMessage("Agent ID is not Active");
					 }
				 }else{
						agentResponse.setResponseCode("300");
						agentResponse.setResponseMessage("Invalid Referral Id");
				 }
			}
		}else {
			agentResponse.setResponseCode("300");
			agentResponse.setResponseMessage("Invalid Referral ID");
		}
		
		
		return null;
	}
}
