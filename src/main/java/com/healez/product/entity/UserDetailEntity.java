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
@Table (name="user_detail")
public class UserDetailEntity {
	
	@Id
	@Column(name="username", nullable = false, length = 20)
	private String userName;

	@Column(name="password", nullable = false, length = 20)
	private String password;

	@Column(name="agent_contact", nullable = false, length = 20)
	private String agentContact;

}
