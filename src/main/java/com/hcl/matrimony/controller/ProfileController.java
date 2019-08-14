package com.hcl.matrimony.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.matrimony.dto.ProfileDto;
import com.hcl.matrimony.service.UserRegistrationService2;

@CrossOrigin(allowedHeaders= {"*","*/"},origins= {"*","*/"})
@RestController
public class ProfileController {
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	@Autowired
	UserRegistrationService2 userRegistrationService2;

	@GetMapping("/profile/{MobileNo}")
	public List<ProfileDto> getListOfProfile(@PathVariable("MobileNo") Long MobileNo) {
		logger.info("******success****");
		return userRegistrationService2.getListOfProfile(MobileNo);

	}

}
