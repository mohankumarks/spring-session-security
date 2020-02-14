package com.mohan.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mohan.security.security.CustomUserDetails;

@RestController
public class DemoController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Map<String, String>> demo() {
		String name = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof CustomUserDetails) {
			name = ((CustomUserDetails) principal).getFullName();
		}

		Map<String, String> result = new HashMap<String, String>();
		result.put("message", "Welcome " + name);

		return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
	}
}
