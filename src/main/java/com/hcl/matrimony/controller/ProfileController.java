package com.hcl.matrimony.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.matrimony.dto.ProfileDto;
import com.hcl.matrimony.service.InterestProfileService;
import com.hcl.matrimony.service.UserRegistrationService2;

@CrossOrigin(allowedHeaders= {"*","*/"},origins= {"*","*/"})
@RestController
public class ProfileController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);
	
	@Autowired
	UserRegistrationService2 userRegistrationService2;
	
	@Autowired
	private InterestProfileService interestProfileService;

	@GetMapping("/profile/{MobileNo}")
	public List<ProfileDto> getListOfProfile(@PathVariable("mobileNo") Long mobileNo) {
		LOGGER.info("******success****");
		return userRegistrationService2.getListOfProfile(mobileNo);

	}
	
	/**
	 * @param mobile
	 * @param place
	 * @param occupation
	 * @return
	 */
	@GetMapping("/profiles/{mobile}/interest/")
	public ResponseEntity<Object> searchProfile(@PathVariable("mobile") long mobile, @RequestParam(value="place", required=false) String place, @RequestParam(value="occupation", required=false) String occupation ){
		
		LOGGER.info("SearchProfileController :: searchProfile",mobile,place,occupation);
		
		return new ResponseEntity<>(interestProfileService.searchProfile(mobile, place, occupation), HttpStatus.OK);
	}

}
