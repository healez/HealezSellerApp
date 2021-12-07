package com.healez.product.serviceimpl;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;

import com.healez.product.data.Address;
import com.healez.product.data.AgentProfile;
import com.healez.product.data.UserInfo;
import com.healez.product.request.AgentRequest;
import com.healez.product.response.AgentResponse;
import com.healez.product.service.AddressService;
import com.healez.product.service.AgentProfileService;
import com.healez.product.service.ConsolidatedInfoService;
import com.healez.product.service.UserInfoService;

public class ConsolidatedInfoServiceImpl implements ConsolidatedInfoService{

	@Autowired
	AgentProfileService agentProfileService;

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	AddressService addressService;

	
	@Override
	public AgentResponse saveDetails(AgentRequest agentRequest) {
	
		AgentResponse agentResponse = new AgentResponse();
		agentResponse.setResponseCode("200");
		agentResponse.setResponseMessage("Information is saved");
		
		
		if(agentRequest!=null ) {
			if(agentRequest.getAgentProfile()!=null) {
				if(agentRequest.getUserInfo()!=null && agentRequest.getUserInfo().getContactNumber()!=null &&
						agentRequest.getUserInfo().getPassword()!=null) {
					if(agentRequest.getAgentAddress()!=null) {
						AgentProfile agentProfile = agentRequest.getAgentProfile();
						agentProfileService.addAgentProfile(agentProfile, agentResponse);
						if(agentResponse.getAgentId()!=null) {
							UserInfo userInfo = agentRequest.getUserInfo();
							userInfo.setStatus("A");
							userInfoService.addUserInfo(userInfo,agentResponse);
							List<Address> agentAddress = agentRequest.getAgentAddress();
							addressService.addAddress(agentAddress, agentResponse);
						}else {
							agentResponse.setResponseCode("00");
							agentResponse.setResponseMessage("Request Failed. Please contact system administrator");
						}
					}else {
						agentResponse.setResponseCode("300");
						agentResponse.setResponseMessage("Request Failed. User Password and Contact is required");
					}
				}else {
					agentResponse.setResponseCode("300");
					agentResponse.setResponseMessage("Request Failed. User Password and Contact is required");
				}
				
			}else {
				agentResponse.setResponseCode("300");
				agentResponse.setResponseMessage("Request Failed. Agent Profile Entry is needed");
			}
			
		}else {
			agentResponse.setResponseCode("300");
			agentResponse.setResponseMessage("Request Failed.");
		}
		return agentResponse;
	}

}
