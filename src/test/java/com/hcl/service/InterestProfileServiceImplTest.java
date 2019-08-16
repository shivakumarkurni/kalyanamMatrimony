package com.hcl.service;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.matrimony.dto.Estatus;
import com.hcl.matrimony.dto.InterestCreationResponse;
import com.hcl.matrimony.entity.InterestShown;
import com.hcl.matrimony.entity.UserProfiles;
import com.hcl.matrimony.repository.InterestShownRepository;
import com.hcl.matrimony.repository.UserProfilesRepository;
import com.hcl.matrimony.service.InterestProfileServiceImpl;
import com.hcl.matrimony.service.MatromonyException;

@RunWith(SpringRunner.class)
public class InterestProfileServiceImplTest {
 
	@InjectMocks
	InterestProfileServiceImpl interestProfileServiceImpl;
	@Mock
	InterestShownRepository interestShownRepository;
	
	@Mock
	UserProfilesRepository userProfilesRepository;
	
	InterestShown interestShown;
	
	UserProfiles  userProfiles;
	UserProfiles  userProfiles2;
	
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
		
		userProfileslist=new  ArrayList<>();
		userProfileslist.add(userProfiles);
		userProfileslist.add(userProfiles2);
		
		interestShownList=new ArrayList<>();
		interestShownList.add(interestShown);
		
	}

	
	
	@Test
	public void interestProfiles() {
		interestShownList=new ArrayList<>();
		
		Mockito.when(userProfilesRepository.findByMobile(userProfiles.getMobile())).thenReturn(userProfileslist);
		Mockito.when(userProfilesRepository.findByMobile(userProfiles2.getMobile())).thenReturn(userProfileslist);

		Mockito.when(interestShownRepository.findByFromMobileAndTargetMobile(userProfiles.getMobile(), userProfiles2.getMobile())).thenReturn(interestShownList);
		Mockito.when(interestShownRepository.save(interestShown)).thenReturn(interestShown);

		ResponseEntity<InterestCreationResponse> actualValue = interestProfileServiceImpl.interestProfiles(userProfiles.getMobile(), userProfiles2.getMobile());
		
		Assert.assertEquals(201, actualValue.getBody().getStatusCode());
	}
	
	@Test(expected = MatromonyException.class)
	public void interestProfilesProfileNotExist() {
		interestShownList=new ArrayList<>();
		
//		Mockito.when(userProfilesRepository.findByMobile(userProfiles.getMobile())).thenReturn(userProfileslist);
//		Mockito.when(userProfilesRepository.findByMobile(userProfiles2.getMobile())).thenReturn(userProfileslist);

		Mockito.when(interestShownRepository.findByFromMobileAndTargetMobile(userProfiles.getMobile(), userProfiles2.getMobile())).thenReturn(interestShownList);
		Mockito.when(interestShownRepository.save(interestShown)).thenReturn(interestShown);

		ResponseEntity<InterestCreationResponse> actualValue = interestProfileServiceImpl.interestProfiles(userProfiles.getMobile(), userProfiles2.getMobile());
		
		Assert.assertEquals(201, actualValue.getBody().getStatusCode());
	}
 
	@Test(expected = MatromonyException.class)
	public void interestProfilesAlreadyRequestRaised() {
		
		Mockito.when(userProfilesRepository.findByMobile(userProfiles.getMobile())).thenReturn(userProfileslist);
		Mockito.when(userProfilesRepository.findByMobile(userProfiles2.getMobile())).thenReturn(userProfileslist);

		Mockito.when(interestShownRepository.findByFromMobileAndTargetMobile(userProfiles.getMobile(), userProfiles2.getMobile())).thenReturn(interestShownList);
		Mockito.when(interestShownRepository.save(interestShown)).thenReturn(interestShown);

		ResponseEntity<InterestCreationResponse> actualValue = interestProfileServiceImpl.interestProfiles(userProfiles.getMobile(), userProfiles2.getMobile());
		
		Assert.assertEquals(201, actualValue.getBody().getStatusCode());
	}
	
	@Test
	public void  interestProfilesUpadte() {
		
		Mockito.when(interestShownRepository.findByFromMobileAndTargetMobile(userProfiles.getMobile(), userProfiles2.getMobile())).thenReturn(interestShownList);
		Mockito.when(interestShownRepository.save(interestShown)).thenReturn(interestShown);
		
		
		ResponseEntity<InterestCreationResponse> actualValue = interestProfileServiceImpl.interestProfilesUpadte(userProfiles.getMobile(), userProfiles2.getMobile(),"accept");
		Assert.assertEquals(201, actualValue.getBody().getStatusCode());

		
	}
	
	@Test(expected = MatromonyException.class)
	public void  interestProfilesUpadtePositive() {
		
		interestShownList=new ArrayList<InterestShown>();
		Mockito.when(interestShownRepository.findByFromMobileAndTargetMobile(userProfiles.getMobile(), userProfiles2.getMobile())).thenReturn(interestShownList);
		Mockito.when(interestShownRepository.save(interestShown)).thenReturn(interestShown);
		
		
		 interestProfileServiceImpl.interestProfilesUpadte(userProfiles.getMobile(), userProfiles2.getMobile(),"accept");
 
		
	} 
	
	
	@Test(expected = MatromonyException.class)
	public void  interestProfilesUpadtePositive2() {
		interestShown.setStatus("kkk");
		interestShownList=new ArrayList<InterestShown>();
		Mockito.when(interestShownRepository.findByFromMobileAndTargetMobile(userProfiles.getMobile(), userProfiles2.getMobile())).thenReturn(interestShownList);
		Mockito.when(interestShownRepository.save(interestShown)).thenReturn(interestShown);
		
		
		 interestProfileServiceImpl.interestProfilesUpadte(userProfiles.getMobile(), userProfiles2.getMobile(),"kk");
 
		
	} 

	
	@Test(expected = MatromonyException.class)
	public void  interestProfilesUpadtePositive3() {
		interestShown.setStatus(Estatus.REJECT.toString());
		interestShownList=new ArrayList<InterestShown>();
		Mockito.when(interestShownRepository.findByFromMobileAndTargetMobile(userProfiles.getMobile(), userProfiles2.getMobile())).thenReturn(interestShownList);
		Mockito.when(interestShownRepository.save(interestShown)).thenReturn(interestShown);
		 interestProfileServiceImpl.interestProfilesUpadte(userProfiles.getMobile(), userProfiles2.getMobile(),"accept");
 
		
	} 
	
	@Test
	public void  searchProfile(){ 
		Mockito.when(userProfilesRepository.findByMobile(userProfiles.getMobile())).thenReturn(userProfileslist);
		Mockito.when(userProfilesRepository.findByPlaceAndOccupationAndGenderNot(userProfiles.getPlace(), userProfiles.getOccupation(),userProfiles.getGender())).thenReturn(userProfileslist);
		Mockito.when(userProfilesRepository.findByPlaceAndGenderNot(userProfiles.getPlace(), userProfiles.getGender())).thenReturn(userProfileslist);
		Mockito.when(userProfilesRepository.findByOccupationAndGenderNot(userProfiles.getOccupation(), userProfiles.getGender())).thenReturn(userProfileslist);
		Mockito.when(userProfilesRepository.findByGenderNot(userProfiles.getGender())).thenReturn(userProfileslist);
		Object actual = interestProfileServiceImpl.searchProfile(userProfiles.getMobile(), userProfiles.getPlace(), userProfiles.getOccupation());
		Assert.assertEquals(actual, actual);
	}
	@Test
	public void  searchProfileNullOccupation(){ 
		Mockito.when(userProfilesRepository.findByMobile(userProfiles.getMobile())).thenReturn(userProfileslist);
		Mockito.when(userProfilesRepository.findByPlaceAndOccupationAndGenderNot(userProfiles.getPlace(), userProfiles.getOccupation(),userProfiles.getGender())).thenReturn(userProfileslist);
		Mockito.when(userProfilesRepository.findByPlaceAndGenderNot(userProfiles.getPlace(), userProfiles.getGender())).thenReturn(userProfileslist);
		Mockito.when(userProfilesRepository.findByOccupationAndGenderNot(userProfiles.getOccupation(), userProfiles.getGender())).thenReturn(userProfileslist);
		Mockito.when(userProfilesRepository.findByGenderNot(userProfiles.getGender())).thenReturn(userProfileslist);
		Object actual = interestProfileServiceImpl.searchProfile(userProfiles.getMobile(), userProfiles.getPlace(), null);
		Assert.assertEquals(actual, actual);
	}
	@Test
	public void  searchProfileNullPlace(){ 
		Mockito.when(userProfilesRepository.findByMobile(userProfiles.getMobile())).thenReturn(userProfileslist);
		Mockito.when(userProfilesRepository.findByPlaceAndOccupationAndGenderNot(userProfiles.getPlace(), userProfiles.getOccupation(),userProfiles.getGender())).thenReturn(userProfileslist);
		Mockito.when(userProfilesRepository.findByPlaceAndGenderNot(userProfiles.getPlace(), userProfiles.getGender())).thenReturn(userProfileslist);
		Mockito.when(userProfilesRepository.findByOccupationAndGenderNot(userProfiles.getOccupation(), userProfiles.getGender())).thenReturn(userProfileslist);
		Mockito.when(userProfilesRepository.findByGenderNot(userProfiles.getGender())).thenReturn(userProfileslist);
		Object actual = interestProfileServiceImpl.searchProfile(userProfiles.getMobile(), null, userProfiles.getOccupation());
		Assert.assertEquals(actual, actual);
	}
}
