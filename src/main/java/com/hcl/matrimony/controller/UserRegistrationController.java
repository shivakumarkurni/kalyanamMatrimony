package com.hcl.matrimony.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.matrimony.dto.RegistrationDto;
import com.hcl.matrimony.service.UserRegistrationService;

/**
 * userRegistration Controller
 * 
 */
@RestController
@CrossOrigin(allowedHeaders={"*","*/"}, origins={"*","*/"})
public class UserRegistrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationController.class);
	@Autowired
	private UserRegistrationService userRegistrationService;
	
	/**
	 * @param resitrationDto
	 * @return 
	 */
	@PostMapping("/profile")
	public ResponseEntity<String> registeration(@RequestBody RegistrationDto resitrationDto){
		
		LOGGER.error(" UserRegistrationController :: "+resitrationDto);
		
		return new ResponseEntity<>(userRegistrationService.registration(resitrationDto), HttpStatus.CREATED);
	}
}
