package com.healez.product.request;

import java.util.List;

import com.healez.product.data.Address;
import com.healez.product.data.AgentProfile;
import com.healez.product.data.UserInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentRequest {
	private AgentProfile agentProfile;
	private List<Address> agentAddress;
	private UserInfo userInfo;
}
