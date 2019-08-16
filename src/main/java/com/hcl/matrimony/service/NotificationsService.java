package com.hcl.matrimony.service;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.matrimony.dto.ResponseDto;
import com.hcl.matrimony.entity.InterestShown;
import com.hcl.matrimony.entity.UserProfiles;
import com.hcl.matrimony.repository.NotificationsRepository;
import com.hcl.matrimony.repository.UserProfilesRepository;

/**
 * @author Shiva
 *
 */

//Notifying the user if any body showed interest
@Service
public class NotificationsService {

	@Autowired
	NotificationsRepository notificationsRepository;

	@Autowired
	UserProfilesRepository userProfilesRepository;

	private final Logger logger = LoggerFactory.getLogger(NotificationsService.class);
	
	/**
	 * @param mobileNo
	 * return ResponseDTO
	 */

	// notifications requested profiles notification
	public List<ResponseDto> notifications(Long mobileNo) {

		logger.info("NotificationsService--->notifications enterd");

		ResponseDto responseDto = null;
		List<ResponseDto> responseList = new ArrayList<>();

		List<InterestShown> interestList = notificationsRepository.findByTargetMobile(mobileNo);

		List<UserProfiles> user = userProfilesRepository.findByMobile(mobileNo);
		List<UserProfiles> userProfile = null;

		if (user.get(0).getAccountType().equalsIgnoreCase("Gold")) {

			for (InterestShown interestShown : interestList) {

				responseDto = new ResponseDto();

				Long mobile = interestShown.getFromMobile();

				userProfile = userProfilesRepository.findByMobile(mobile);

				responseDto.setName(userProfile.get(0).getName());
				responseDto.setMobileNo(userProfile.get(0).getMobile());
				responseDto.setOccupation(userProfile.get(0).getOccupation());
				responseDto.setPlace(userProfile.get(0).getPlace());
				responseDto.setDob(userProfile.get(0).getDateOfBirth());

				responseList.add(responseDto);

			} 

		}

		else {

			for (InterestShown interestShown : interestList) {

				responseDto = new ResponseDto();

				Long mobile = interestShown.getFromMobile();

				userProfile = userProfilesRepository.findByMobile(mobile);

				responseDto.setName(userProfile.get(0).getName());
				responseDto.setOccupation(userProfile.get(0).getOccupation());
				responseDto.setMobileNo(0L);
				responseDto.setPlace("");
				responseList.add(responseDto);
			}

		}

		logger.info("NotificationsService--->notifications complete");

		return responseList;

	}

}
