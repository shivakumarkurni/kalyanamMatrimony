package com.hcl.matrimony.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.matrimony.dto.ResponseDto;

import com.hcl.matrimony.service.NotificationsService;

@RestController
public class NotificationsController {

	@Autowired
	NotificationsService notificationsService;

	@GetMapping("/profiles/notifications/{mobileNo}")
	public ResponseEntity<List<ResponseDto>> notifications(@PathVariable Long mobileNo) {

		return new ResponseEntity<>(notificationsService.notifications(mobileNo), HttpStatus.OK);

	}

}
