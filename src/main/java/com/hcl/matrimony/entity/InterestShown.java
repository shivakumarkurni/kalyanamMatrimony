package com.hcl.matrimony.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "INTRESTSHOWN")
@Setter
@Getter
public class InterestShown {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long interestId;
	private String fromId;
	private String targetId;
	private LocalDateTime date;
	private String targetIdInterest;
	
	

}
