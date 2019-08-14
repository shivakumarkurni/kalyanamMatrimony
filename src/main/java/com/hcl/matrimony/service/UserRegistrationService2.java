package com.hcl.matrimony.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.matrimony.dto.LoginDto;
import com.hcl.matrimony.dto.ProfileDto;
import com.hcl.matrimony.entity.UserProfiles;
import com.hcl.matrimony.repository.UserProfilesRepository;

@Service
public class UserRegistrationService2 {
	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationService.class);
	@Autowired
	UserProfilesRepository userProfilesRepository;

	public String userLogin(LoginDto loginDto) {
		Long mobileNo = loginDto.getMobile();
		String password = loginDto.getPassword();
		List<UserProfiles> userProfiles2 = userProfilesRepository.findByMobile(mobileNo);
		UserProfiles userProfiles = userProfiles2.get(0);

		if (userProfiles != null) {
			if (userProfiles.getPassword().equals(password)) {
				logger.info("************successfully login*************");
				return "successfully login";
			} else {
				throw new MatromonyException("Incorrect MobileNo and Password");
			}
		} else {
			throw new MatromonyException("User Mobile No is not Registered");
		}
	}

	public List<ProfileDto> getListOfProfile(Long mobileNo) {
		List<UserProfiles> userProfiles3 = userProfilesRepository.findByMobile(mobileNo);
		UserProfiles userProfiles= userProfiles3.get(0);

		String gender = userProfiles.getGender();
		logger.info("************gender************:" + gender);

		List<ProfileDto> profileDtoList = new ArrayList<>();

		List<UserProfiles> userProfiles2 = new ArrayList<>();

		if (gender.equalsIgnoreCase("MALE")) {
			userProfiles2 = userProfilesRepository.findByGender("FEMALE");

		} else {
			userProfiles2 = userProfilesRepository.findByGender("MALE");
		}

		if (userProfiles.getAccountType().trim().equalsIgnoreCase("gold")) {

			for (UserProfiles user : userProfiles2) {
				ProfileDto profileDto = new ProfileDto();
				profileDto.setGender(user.getGender());
				profileDto.setMobile(user.getMobile());
				profileDto.setName(user.getName());
				profileDto.setPlace(user.getPlace());
				profileDto.setDateOfBirth(user.getDateOfBirth());
				profileDto.setOccupation(user.getOccupation());
				profileDtoList.add(profileDto);
			}
		} else {

			for (UserProfiles user : userProfiles2) {
				ProfileDto profileDto = new ProfileDto();
				profileDto.setGender(user.getGender());
				profileDto.setName(user.getName());
				profileDto.setDateOfBirth(user.getDateOfBirth());

				profileDtoList.add(profileDto);
			}
		}
		return profileDtoList;
	}

}
