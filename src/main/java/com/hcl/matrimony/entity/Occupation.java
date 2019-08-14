package com.hcl.matrimony.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Occupation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long occupationId;
	private String occupation;

}
