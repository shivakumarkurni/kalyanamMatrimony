package com.hcl.matrimony.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.matrimony.dto.InterestCreationResponse;
import com.hcl.matrimony.entity.InterestShown;
import com.hcl.matrimony.repository.InterestShownRepository;
import com.hcl.matrimony.repository.UserProfilesRepository;

@Service
public class InterestProfileServiceImpl implements InterestProfileService {

	@Autowired
	InterestShownRepository interestShownRepository;
	@Autowired
	UserProfilesRepository userProfilesRepository;

	@Override
	public ResponseEntity<InterestCreationResponse> interestProfiles(Long fromMobile, Long targetMobile) {

		if (userProfilesRepository.findByMobile(fromMobile).isEmpty()
				|| userProfilesRepository.findByMobile(targetMobile).isEmpty())
			throw new MatromonyException(" profiles not existed");

		if (!interestShownRepository.findByFromMobileAndTargetMobile(fromMobile, targetMobile).isEmpty())
			throw new MatromonyException(" alreay interest  created");

		InterestShown interestedShown = new InterestShown();
		interestedShown.setDate(LocalDateTime.now());
		interestedShown.setFromMobile(fromMobile);
		interestedShown.setTargetMobile(targetMobile);
		interestShownRepository.save(interestedShown);

		InterestCreationResponse interestCreationResponse = new InterestCreationResponse();
		interestCreationResponse.setMessage("interest crated succsessfully");
		interestCreationResponse.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(interestCreationResponse);

	}

	@Override
	public ResponseEntity<InterestCreationResponse> interestProfilesUpadte(Long fromMobile, Long targetMobile,
			String status) {

		List<InterestShown> interestShown = interestShownRepository.findByFromMobileAndTargetMobile(fromMobile,
				targetMobile);
		if (interestShown.isEmpty())
			throw new MatromonyException(" not interest profiles found");

		interestShown.get(0).setStatus("true");
		interestShownRepository.save(interestShown.get(0));

		InterestCreationResponse interestCreationResponse = new InterestCreationResponse();
		interestCreationResponse.setMessage(" status succsessfully updated");
		interestCreationResponse.setStatusCode(HttpStatus.CREATED.value());

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(interestCreationResponse);

	}

}
