package com.hcl.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.matrimony.controller.LoginController;
import com.hcl.matrimony.controller.ProfileController;
import com.hcl.matrimony.controller.UserRegistrationController;
import com.hcl.matrimony.dto.InterestCreationInput;
import com.hcl.matrimony.service.InterestProfileServiceImpl;
import com.hcl.matrimony.service.UserRegistrationService;
import com.hcl.matrimony.service.UserRegistrationService2;
 
@RunWith(SpringRunner.class)
public class UserRegistrationControllerTest {

	@InjectMocks
	UserRegistrationController userRegistrationController;
	
	@InjectMocks
	LoginController loginController;
	
	@Mock
	UserRegistrationService2 userRegistrationService2;
	
	@InjectMocks
	ProfileController ProfileController;
	
	@Mock
	InterestProfileServiceImpl interestProfileServiceImpl;
	@Mock
	private UserRegistrationService userRegistrationService;

	MockMvc mockMvc;
	MockMvc mockMvc2;
	MockMvc mockMvc3;

	InterestCreationInput interestCreationInput;

	@Before
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.standaloneSetup(userRegistrationController).build();
		mockMvc2 = MockMvcBuilders.standaloneSetup(loginController).build();
		mockMvc3 = MockMvcBuilders.standaloneSetup(ProfileController).build();

		interestCreationInput = new InterestCreationInput();
		interestCreationInput.setFromMobile(999999L);
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void transaction() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/profile/interest").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(interestCreationInput))).andExpect(status().isOk());

	}

	@Test
	public void interestProfilesUpdate() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.put("/profile/interest").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(interestCreationInput))).andExpect(status().isOk());

	}

	@Test
	public void registeration() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/profile").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(interestCreationInput))).andExpect(status().isCreated());

	}
	
	@Test
	public void login() throws Exception {

		mockMvc2.perform(MockMvcRequestBuilders.post("/profile/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(interestCreationInput))).andExpect(status().isOk());

	}

	
	@Test
	public void profile() throws Exception {

		mockMvc3.perform(MockMvcRequestBuilders.get("/profile/999").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(interestCreationInput))).andExpect(status().isOk());

	}

}
