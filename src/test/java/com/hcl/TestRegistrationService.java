package com.hcl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.matrimony.dto.RegistrationDto;
import com.hcl.matrimony.entity.UserProfiles;
import com.hcl.matrimony.repository.UserProfilesRepository;
import com.hcl.matrimony.service.UserRegistreationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestRegistrationService {

	@InjectMocks
	private UserRegistreationServiceImpl userRegistrationImpl;
	
	@Mock
	private UserProfilesRepository userProfileRepo;
	
	@Mock
	RegistrationDto registrationDto;
	
	@Mock
	UserProfiles userProfiles; 
	
	@Test
	public void testRegistration(){
		String expectedMessage = "Registration successfully.";
		Mockito.when(userProfileRepo.save(Mockito.anyObject())).thenReturn(userProfiles);
		String actualMessage = userRegistrationImpl.registration(registrationDto);
		assertEquals(actualMessage, expectedMessage);
	}
}
