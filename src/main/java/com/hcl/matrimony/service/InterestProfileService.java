package com.hcl.matrimony.service;

import org.springframework.http.ResponseEntity;

import com.hcl.matrimony.dto.InterestCreationResponse;

public interface InterestProfileService {
	public ResponseEntity<InterestCreationResponse> interestProfiles(Long fromMobile, Long targetMobile);
	
	public ResponseEntity<InterestCreationResponse> interestProfilesUpadte(Long fromMobile, Long targetMobile, String status) ;

	public Object searchProfile(long mobile, String place, String occupation);

}
