package com.hcl.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.matrimony.controller.UserRegistrationController;
import com.hcl.matrimony.dto.InterestCreationInput;
import com.hcl.matrimony.service.InterestProfileServiceImpl;
import com.hcl.matrimony.service.UserRegistrationService;

@RunWith(SpringRunner.class)
public class UserRegistrationControllerTest {

	@InjectMocks
	UserRegistrationController userRegistrationController;

	@Mock
	InterestProfileServiceImpl interestProfileServiceImpl;
	@Mock
	private UserRegistrationService userRegistrationService;

	MockMvc mockMvc;

	InterestCreationInput interestCreationInput;

	@Before
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.standaloneSetup(userRegistrationController).build();
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

}
