package com.hcl.matrimony.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.matrimony.dto.LoginDto;
import com.hcl.matrimony.dto.ProfileDto;
import com.hcl.matrimony.entity.InterestShown;
import com.hcl.matrimony.entity.UserProfiles;
import com.hcl.matrimony.repository.UserProfilesRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationService2Test {
	
	
	@InjectMocks UserRegistrationService2 userRegistrationService2;
	
	@Mock UserProfilesRepository userProfilesRepository;
	
	

	InterestShown interestShown;
	
	UserProfiles  userProfiles;
	UserProfiles  userProfiles2;
	
	LoginDto loginDto;
	
	List<UserProfiles> userProfileslist;
	List<InterestShown> interestShownList;

	
	@Before
	public void setup() {
		
		interestShown=new InterestShown();
		
		interestShown.setDate(LocalDateTime.now());
		interestShown.setFromMobile(9999999999L);
		interestShown.setInterestId(1L);
		interestShown.setStatus("accept");
		interestShown.setTargetMobile(8888888888L);
		
		
		userProfiles=new UserProfiles();
		userProfiles.setAccountType("gold");
		userProfiles.setDateOfBirth(LocalDate.now());
		userProfiles.setPlace("banglore");
		userProfiles.setGender("male");
		userProfiles.setMobile(9999999999L);
		userProfiles.setName("sai");
		userProfiles.setOccupation("working");
		userProfiles.setPassword("1234");
		userProfiles.setUserId(1L);
		
		userProfiles2=new UserProfiles();
		userProfiles2.setAccountType("gold");
		userProfiles2.setDateOfBirth(LocalDate.now());
		userProfiles2.setPlace("banglore");
		userProfiles2.setGender("male");
		userProfiles2.setMobile(9999999999L);
		userProfiles2.setName("sai");
		userProfiles2.setOccupation("working");
		userProfiles2.setPassword("1234");
		userProfiles2.setUserId(1L);
		
		loginDto=new LoginDto();
		loginDto.setMobile(userProfiles.getMobile());
		loginDto.setPassword(userProfiles.getPassword());

		
		userProfileslist=new  ArrayList<>();
		userProfileslist.add(userProfiles);
		userProfileslist.add(userProfiles2);
		
		interestShownList=new ArrayList<>();
		interestShownList.add(interestShown);
		
	}
	
	 
	@Test
	public void userLogin() {
	Mockito.when(userProfilesRepository.findByMobile(loginDto.getMobile())).thenReturn(userProfileslist);
	
	String actual = userRegistrationService2.userLogin(loginDto);
	Assert.assertEquals("successfully login", actual);
	
	}
	
	@Test(expected = MatromonyException.class)
	public void userLoginNegatie() {
		userProfileslist=new ArrayList<>();
	Mockito.when(userProfilesRepository.findByMobile(loginDto.getMobile())).thenReturn(userProfileslist);
	
	 userRegistrationService2.userLogin(loginDto);
	
	}
	 
	
	@Test(expected = MatromonyException.class)
	public void userLoginFaild() {
		loginDto.setPassword("fidfi");
		userProfileslist.get(0).setPassword("1234");
		userProfileslist=new ArrayList<>();
	Mockito.when(userProfilesRepository.findByMobile(loginDto.getMobile())).thenReturn(userProfileslist);
	
	 userRegistrationService2.userLogin(loginDto);
	
	}
	
	
	@Test
	public void getListOfProfile() {
		userProfiles.setGender("FEMALE");
		userProfiles.setMobile(9999999999L);
		userProfileslist=new  ArrayList<>();
		userProfileslist.add(userProfiles);
		
		
		Mockito.when(userProfilesRepository.findByMobile(loginDto.getMobile())).thenReturn(userProfileslist);

		Mockito.when(userProfilesRepository.findByGender("MALE")).thenReturn(userProfileslist);
		List<ProfileDto> actual = userRegistrationService2.getListOfProfile(loginDto.getMobile());

		Assert.assertEquals(userProfileslist.size(), actual.size());

	}
	
	
	@Test
	public void getListOfProfile2() {
		userProfiles.setGender("MALE");
		userProfiles.setOccupation("gold");
		userProfiles.setMobile(9999999999L);
		userProfileslist=new  ArrayList<>();
		userProfileslist.add(userProfiles);
		
		
		Mockito.when(userProfilesRepository.findByMobile(loginDto.getMobile())).thenReturn(userProfileslist);

//		Mockito.when(userProfilesRepository.findByGender("MALE")).thenReturn(userProfileslist);
		List<ProfileDto> actual = userRegistrationService2.getListOfProfile(loginDto.getMobile());

		Assert.assertEquals(0, actual.size());

	}

	
	@Test
	public void getListOfProfileFemale() {
		userProfiles.setGender("MALE");
		userProfiles.setOccupation("gold");
		userProfiles.setMobile(9999999999L);
		userProfileslist=new  ArrayList<>();
		userProfileslist.add(userProfiles);
		
		
		Mockito.when(userProfilesRepository.findByMobile(loginDto.getMobile())).thenReturn(userProfileslist);

		Mockito.when(userProfilesRepository.findByGender("FEMALE")).thenReturn(userProfileslist);
		List<ProfileDto> actual = userRegistrationService2.getListOfProfile(loginDto.getMobile());

		Assert.assertEquals(userProfileslist.size(), actual.size());

	}
	
	@Test
	public void getListOfProfileFemaleFree() {
		userProfiles.setAccountType("free");
		
		
		Mockito.when(userProfilesRepository.findByMobile(loginDto.getMobile())).thenReturn(userProfileslist);

		Mockito.when(userProfilesRepository.findByGender("FEMALE")).thenReturn(userProfileslist);
		List<ProfileDto> actual = userRegistrationService2.getListOfProfile(loginDto.getMobile());

		Assert.assertEquals(userProfileslist.size(), actual.size());

	}

}
