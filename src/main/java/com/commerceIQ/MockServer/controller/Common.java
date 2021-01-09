package com.commerceIQ.MockServer.controller;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commerceIQ.MockServer.util.AuthorUtil;

@RestController
public class Common {
	
	@GetMapping (value = "/getAll")
	 public String getAll()
	 {
		 return getAuthorUtilObject().readDataFromFile();
	 }

	
	@Lookup
	AuthorUtil getAuthorUtilObject() {
		return null;
	}

	
	
	
	
	
	
	
	
	
}
