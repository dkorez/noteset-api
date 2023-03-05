package com.noteset.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/version")
public class VersionController {

	@GetMapping("")
	public ResponseEntity<?> getVersion() {
		String version = "0.0.1-dev";
		
		return new ResponseEntity<String>(version, HttpStatus.OK);
	}
}
