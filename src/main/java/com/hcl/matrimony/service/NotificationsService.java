package com.hcl.matrimony.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.matrimony.dto.ResponseDto;
import com.hcl.matrimony.entity.InterestShown;
import com.hcl.matrimony.entity.UserProfiles;
import com.hcl.matrimony.repository.NotificationsRepository;
import com.hcl.matrimony.repository.UserProfilesRepository;

@Service
public class NotificationsService {

	@Autowired
	NotificationsRepository notificationsRepository;

	@Autowired
	UserProfilesRepository userProfilesRepository;

	public List<ResponseDto> notifications(Long mobileNo) {

		ResponseDto responseDto = null;
		List<ResponseDto> responseList = new ArrayList<>();

		List<InterestShown> interestList = notificationsRepository.findByTargetMobile(mobileNo);

		UserProfiles user = userProfilesRepository.findByMobile(mobileNo);
		UserProfiles userProfile = null;

		if (user.getAccountType().equalsIgnoreCase("Gold")) {

			for (InterestShown interestShown : interestList) {

				responseDto = new ResponseDto();

				Long mobile = interestShown.getFromMobile();

				userProfile = userProfilesRepository.findByMobile(mobile);

				responseDto.setName(userProfile.getName());
				responseDto.setMobileNo(userProfile.getMobile());
				responseDto.setOccupation(userProfile.getOccupation());
				responseDto.setPlace(userProfile.getPlace());
				responseDto.setDob(userProfile.getDateOfBirth());

				responseList.add(responseDto);

			}

		}

		else {

			for (InterestShown interestShown : interestList) {

				responseDto = new ResponseDto();

				Long mobile = interestShown.getFromMobile();

				userProfile = userProfilesRepository.findByMobile(mobile);

				responseDto.setName(userProfile.getName());
				responseDto.setOccupation(userProfile.getOccupation());

				responseList.add(responseDto);
			}

		}

		return responseList;

	}

}
