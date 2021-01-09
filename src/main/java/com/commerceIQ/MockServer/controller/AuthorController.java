package com.commerceIQ.MockServer.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.commerceIQ.MockServer.requestmodel.AuthorRequestModel;
import com.commerceIQ.MockServer.responsemodel.ResponseModel;
import com.commerceIQ.MockServer.service.AuthorService;

@RestController
public class AuthorController {
	private static final Logger log = LogManager.getLogger(AuthorController.class);


	@Autowired
	private AuthorService authorService ;
	
	private ResponseModel responseModel;
		
	@PostMapping(value = "/setAuthor")
	public ResponseEntity<Object> setAuthor(@RequestBody AuthorRequestModel authorRequestModel){
	
		log.info("inside setAuthor");
	//	log.error("error {}", e.getMessage());                catch block of util
		responseModel= getResponseModelObj();
		
		getAuthorServiceObj().storeAuthorData(authorRequestModel, responseModel);
		JSONObject res = new JSONObject();
		res.putOpt("ResponseCode", responseModel.getResponseCode());
		res.putOpt("ResponseMessage", responseModel.getResponseData());
		
		log.info("setAuthor completed {}", responseModel.getResponseCode());
		return new ResponseEntity<>(res.toString(),HttpStatus.OK);	
	}
	
	
	@GetMapping( value ="/getAllData")
	public ResponseEntity<Object> getAllData()
	{
		responseModel= getResponseModelObj();
		getAuthorServiceObj().getAllData(responseModel);
		System.out.println(responseModel.toString());
		JSONArray arr = new JSONArray(responseModel.getResponseData().toString());
		
		
		return new ResponseEntity<>(arr.toString(),HttpStatus.OK);	
		
	}
	
	
	@Lookup
	AuthorService getAuthorServiceObj()
	{
		return null;
		
	}
	
	
	
	
	
	@Lookup
	ResponseModel getResponseModelObj() {
		return null;
	}
}

	

