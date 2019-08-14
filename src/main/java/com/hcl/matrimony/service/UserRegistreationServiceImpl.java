package com.hcl.matrimony.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.matrimony.dto.RegistrationDto;
import com.hcl.matrimony.entity.UserProfiles;
import com.hcl.matrimony.repository.UserProfilesRepository;
/**
 * @author laxman
 *
 */
@Service
public class UserRegistreationServiceImpl implements UserRegistrationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistreationServiceImpl.class);
	
	@Autowired
	private UserProfilesRepository userProfileRepository;
	
	/**
	 * @param RegistrationDto
	 * return String
	 */
	@Override
	public String registration(RegistrationDto registrationDto) {
		
		LOGGER.error("UserRegistreationServiceImpl :: registration - start");
		
		String returnValue = "Registration failed!";
		UserProfiles userProfiles = new UserProfiles();
		BeanUtils.copyProperties(registrationDto, userProfiles);
		if(userProfileRepository.save(userProfiles)!=null){
			returnValue = "Registration successfully.";
		}
		
		LOGGER.error("UserRegistreationServiceImpl :: registration - End");
		
		return returnValue;
	}

}
