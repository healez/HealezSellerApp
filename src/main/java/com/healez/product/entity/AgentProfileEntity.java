package com.healez.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="agent_profile")
public class AgentProfileEntity {
	
	@Id
	@Column(name="agent_id", nullable = false, length = 20)
	private String id;
	
	@Column(name="first_name", nullable = false, length = 50)
	private String firstName;
	
	@Column(name="last_name", nullable = false, length = 50)
	private String lastName;
	
	@Column(name="passport_ic_num", nullable = false, length = 30)
	private String passportNumber;
	
	@Column(name="gender", nullable = false, length = 1)
	private String gender;
	
	@Column(name="email", nullable = false, length = 30)
	private String email;
	
	@Column(name="referral_code", nullable = false, length = 20)
	private String referralCode;
	

}
