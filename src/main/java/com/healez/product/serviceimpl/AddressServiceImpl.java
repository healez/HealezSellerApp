package com.healez.product.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.healez.product.data.Address;
import com.healez.product.data.AddressId;
import com.healez.product.entity.AddressEntity;
import com.healez.product.repository.AddressRepo;
import com.healez.product.response.AgentResponse;
import com.healez.product.service.AddressService;

public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressRepo addRepo;
	
	@Override
	public AgentResponse addAddress(List<Address> addressList, AgentResponse agentResponse) {

		List<AddressEntity> aeList = new ArrayList<AddressEntity>();
		
		for(Address address: addressList) {

			AddressEntity ae = new AddressEntity();
			ae.setAddressNumber(address.getAddressNumber());
			ae.setAddressStreetName(address.getAddressStreetName());
			ae.setAddressCity(address.getAddressCity());
			ae.setAddressPostalCode(address.getAddressPostalCode());
			ae.setAddressState(address.getAddressState());
			ae.setAddressCountryCode(address.getAddressCountryCode());
			ae.setAgentId(agentResponse.getAgentId());
			ae = addRepo.save(ae);
			aeList.add(ae);
		}
		

		List<AddressId> addIdList = new ArrayList<AddressId>();
		for(AddressEntity ae :aeList) {
			AddressId addId = new AddressId();
			addId.setAddressId(ae.getAddressId());
			addId.setAddressCity(ae.getAddressCity());
			addId.setAddressCountryCode(ae.getAddressCountryCode());
			addId.setAddressNumber(ae.getAddressNumber());
			addId.setAddressPostalCode(ae.getAddressPostalCode());
			addId.setAddressState(ae.getAddressState());
			addId.setAddressStreetName(ae.getAddressStreetName());
			addIdList.add(addId);
		}
		agentResponse.setAddressList(addIdList);
		return agentResponse;
	}

}
