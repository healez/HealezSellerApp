package com.healez.product.service;

import java.util.List;

import com.healez.product.data.Address;
import com.healez.product.response.AgentResponse;

public interface AddressService {

	public AgentResponse addAddress(List<Address> address, AgentResponse agentResponse);
}
