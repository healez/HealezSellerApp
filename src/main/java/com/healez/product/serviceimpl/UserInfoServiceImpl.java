package com.healez.product.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.healez.product.data.UserInfo;
import com.healez.product.entity.UserDetailEntity;
import com.healez.product.repository.UserDetailRepo;
import com.healez.product.response.AgentResponse;
import com.healez.product.service.UserInfoService;

public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	UserDetailRepo userRepo;
	
	@Bean
	private  PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public AgentResponse addUserInfo(UserInfo userInfo, AgentResponse agentResponse) {
		
		UserDetailEntity ue = new UserDetailEntity();
		//to encrypt
		ue.setPassword(passwordEncoder().encode(userInfo.getPassword()));
		ue.setAgentContact(userInfo.getContactNumber());
		ue.setUserName(agentResponse.getAgentId());
		ue.setAgentStatus(userInfo.getStatus());
		
		ue = userRepo.save(ue);
		
		userInfo.setPassword(ue.getPassword());
		agentResponse.setUserInfo(userInfo);
		return agentResponse;
	}

}
