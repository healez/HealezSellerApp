package com.healez.product.service;

import com.healez.product.data.AgentProfile;
import com.healez.product.response.AgentResponse;
import com.healez.product.response.VerifyAgentResponse;

public interface AgentProfileService {
	
	public AgentResponse addAgentProfile(AgentProfile agentProfile, AgentResponse agentResponse);
	
	public VerifyAgentResponse verifyAgent(String agentId);
}
