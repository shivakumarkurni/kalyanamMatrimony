package com.hcl.matrimony.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.matrimony.dto.LoginDto;
import com.hcl.matrimony.service.UserRegistrationService2;

@CrossOrigin(allowedHeaders= {"*","*/"},origins= {"*","*/"})
@RestController
public class LoginController {
	 private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	UserRegistrationService2 userRegistrationService2;

	@PostMapping("/profile/login")
	public ResponseEntity<String> userLogin(@RequestBody LoginDto loginDto) {
		logger.info("********userLogin******");
		return new ResponseEntity<String>(userRegistrationService2.userLogin(loginDto), HttpStatus.OK);
	} 
}
