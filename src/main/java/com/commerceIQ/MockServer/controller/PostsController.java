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

import com.commerceIQ.MockServer.requestmodel.PostsRequestModel;
import com.commerceIQ.MockServer.responsemodel.ResponseModel;
import com.commerceIQ.MockServer.service.PostsService;

@RestController
public class PostsController {
	
	private static final Logger log = LogManager.getLogger(PostsController.class);


	@Autowired
	private PostsService PostsService ;
	
	private ResponseModel responseModel;
		
	@PostMapping(value = "/setPosts")
	public ResponseEntity<Object> setPosts(@RequestBody PostsRequestModel PostsRequestModel){
	
		log.info("inside setPosts");
		responseModel= getResponseModelObj();
		
		getPostsServiceObj().storePostsData(PostsRequestModel, responseModel);
		JSONObject res = new JSONObject();
		res.putOpt("ResponseCode", responseModel.getResponseCode());
		res.putOpt("ResponseMessage", responseModel.getResponseData());
		
		log.info("setPosts completed {}", responseModel.getResponseCode());
		return new ResponseEntity<>(res.toString(),HttpStatus.OK);	
	}
	
	
	@GetMapping( value ="/getAllPostsData")
	public ResponseEntity<Object> getAllData()
	{
		responseModel= getResponseModelObj();
		getPostsServiceObj().getAllData(responseModel);
		System.out.println(responseModel.toString());
		JSONArray arr = new JSONArray(responseModel.getResponseData().toString());
		return new ResponseEntity<>(arr.toString(),HttpStatus.OK);		
	}
		
	
	
	@Lookup
	PostsService getPostsServiceObj()
	{
		return null;
		
	}
	
	
	@Lookup
	ResponseModel getResponseModelObj() {
		return null;
	}
}

	

