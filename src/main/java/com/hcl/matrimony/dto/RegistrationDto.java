package com.hcl.matrimony.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegistrationDto {

	private String name;
	private String password;
	private LocalDate dateOfBirth;
	private String place;
	private String gender;
	private Long mobile;
	private String occupation;
	private String accountType;

}
