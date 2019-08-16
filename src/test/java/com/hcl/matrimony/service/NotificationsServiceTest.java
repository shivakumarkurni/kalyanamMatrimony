package com.hcl.matrimony.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.matrimony.dto.ResponseDto;
import com.hcl.matrimony.entity.InterestShown;
import com.hcl.matrimony.entity.UserProfiles;
import com.hcl.matrimony.repository.NotificationsRepository;
import com.hcl.matrimony.repository.UserProfilesRepository;

@RunWith(MockitoJUnitRunner.class)
public class NotificationsServiceTest {

	@Mock
	NotificationsRepository notificationsRepository;

	@Mock 
	UserProfilesRepository userProfilesRepository;

	@InjectMocks
	NotificationsService notificationsService;

	@Test
	public void testNotifications() {

		UserProfiles userProfile = new UserProfiles();

		List<ResponseDto> response = new ArrayList<>();

		List<InterestShown> responseList = new ArrayList<>();
		List<UserProfiles> userProfileslist = new ArrayList<>();

		ResponseDto responseDto = new ResponseDto();

		userProfile.setUserId(1L);
		userProfile.setName("raja");
		userProfile.setPassword("raja@123");
		userProfile.setMobile(9999999999L);
		userProfile.setPlace("bngl");
		userProfile.setOccupation("employee");
		userProfile.setGender("male");
		userProfile.setDateOfBirth(LocalDate.now());
		userProfile.setAccountType("Gold");
		
		UserProfiles userProfile1 = new UserProfiles();
		
		userProfile1.setUserId(1L);
		userProfile1.setName("raja");
		userProfile1.setPassword("raja@123");
		userProfile1.setMobile(2345678L);
		userProfile1.setPlace("bngl");
		userProfile1.setOccupation("employee");
		userProfile1.setGender("male");
		userProfile1.setDateOfBirth(LocalDate.now());
		userProfile1.setAccountType("Gold");

		InterestShown interests = new InterestShown();

		interests.setFromMobile(2345678L);
		interests.setInterestId(1L);
		interests.setStatus("");
		interests.setTargetMobile(9999999999L);
		interests.setDate(LocalDateTime.now());

		responseDto.setDob(userProfile.getDateOfBirth());
		responseDto.setMobileNo(userProfile.getMobile());
		responseDto.setName(userProfile.getName());
		responseDto.setPlace(userProfile.getPlace());
		responseDto.setOccupation(userProfile.getOccupation());
		
		responseList.add(interests);
		userProfileslist.add(userProfile1);

		Mockito.when(notificationsRepository.findByTargetMobile(Mockito.anyLong())).thenReturn(responseList);

		Mockito.when(userProfilesRepository.findByMobile(Mockito.anyLong())).thenReturn(userProfileslist);
		response.add(responseDto);
		
		List<ResponseDto>actual=notificationsService.notifications(interests.getTargetMobile());
		assertEquals(response.size(), actual.size());

	}
	
	
	@Test
	public void testNotificationsForElse() {

		UserProfiles userProfile = new UserProfiles();

		List<ResponseDto> response = new ArrayList<>();

		List<InterestShown> responseList = new ArrayList<>();

		ResponseDto responseDto = new ResponseDto();

		userProfile.setUserId(1L);
		userProfile.setName("raja");
		userProfile.setPassword("raja@123");
		userProfile.setMobile(9999999999L);
		userProfile.setPlace("bngl");
		userProfile.setOccupation("employee");
		userProfile.setGender("male");
		userProfile.setDateOfBirth(LocalDate.now());
		userProfile.setAccountType("Free");
		
		UserProfiles userProfile1 = new UserProfiles();
		
		userProfile1.setUserId(1L);
		userProfile1.setName("raja");
		userProfile1.setPassword("raja@123");
		userProfile1.setMobile(2345678L);
		userProfile1.setPlace("bngl");
		userProfile1.setOccupation("employee");
		userProfile1.setGender("male");
		userProfile1.setDateOfBirth(LocalDate.now());
		userProfile1.setAccountType("free");

		InterestShown interests = new InterestShown();

		interests.setFromMobile(2345678L);
		interests.setInterestId(1L);
		interests.setStatus("");
		interests.setTargetMobile(9999999999L);
		interests.setDate(LocalDateTime.now());

		responseDto.setDob(userProfile.getDateOfBirth());
		responseDto.setMobileNo(userProfile.getMobile());
		responseDto.setName(userProfile.getName());
		responseDto.setPlace(userProfile.getPlace());
		responseDto.setOccupation(userProfile.getOccupation());
		
		responseList.add(interests);
		List<UserProfiles> userProfileslist = new ArrayList<>();

		userProfileslist.add(userProfile1);
		Mockito.when(notificationsRepository.findByTargetMobile(Mockito.anyLong())).thenReturn(responseList);

		Mockito.when(userProfilesRepository.findByMobile(Mockito.anyLong())).thenReturn(userProfileslist);
		response.add(responseDto);
		
		List<ResponseDto>actual=notificationsService.notifications(interests.getTargetMobile());
		assertEquals(response.size(), actual.size());

	}


}
