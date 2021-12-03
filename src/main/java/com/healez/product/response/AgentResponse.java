package com.healez.product.response;

import java.util.List;

import com.healez.product.data.AddressId;
import com.healez.product.data.AgentProfile;
import com.healez.product.data.UserInfo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AgentResponse extends GenericResponse{
	
	private String agentId;
	private AgentProfile agentProfile;
	private List<AddressId> addressList;
	private UserInfo userInfo;
}
