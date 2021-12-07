package com.healez.product.response;


import com.healez.product.data.VerifyAgent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyAgentResponse extends GenericResponse {
	
	public VerifyAgent agentInformation;

}
