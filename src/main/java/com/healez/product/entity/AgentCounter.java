package com.healez.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="agent_profile_counter")
public class AgentCounter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq_agent")
	@SequenceGenerator(name="seq_agent",allocationSize=1)
	@Column(name="id", nullable = false)
	private long id;


}
