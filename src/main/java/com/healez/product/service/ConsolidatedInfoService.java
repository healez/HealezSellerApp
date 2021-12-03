package com.healez.product.service;

import com.healez.product.request.AgentRequest;
import com.healez.product.response.AgentResponse;

public interface ConsolidatedInfoService {

	AgentResponse saveDetails (AgentRequest agentRequest);
}
