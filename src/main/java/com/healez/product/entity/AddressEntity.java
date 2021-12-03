package com.healez.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="agent_address")
public class AddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq_address")
	@SequenceGenerator(name="seq_address",allocationSize=1)
	@Column(name="address_id", nullable = false)
	private long addressId;
	
	@Column(name="address_number", nullable = false, length=20)
	private String addressNumber;
	
	@Column(name="address_streetName", nullable = false, length=0)
	private String addressStreetName;

	@Column(name="address_city", nullable = false, length=30)
	private String addressCity;

	@Column(name="address_postal_code", nullable = false, length=20)
	private String addressPostalCode;
	
	@Column(name="address_state", nullable = false, length=20)
	private String addressState;
	
	@Column(name="address_country_code", nullable = false, length=5)
	private String addressCountryCode;
	
	@OneToOne()
	@JoinColumn(name="agentProfile")
	private AgentProfileEntity agentProfile;
	
}
