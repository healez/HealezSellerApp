package com.healez.product.service;

import com.healez.product.data.UserInfo;
import com.healez.product.response.AgentResponse;

public interface UserInfoService {

	public AgentResponse addUserInfo(UserInfo userInfo, AgentResponse agentResponse);
}
