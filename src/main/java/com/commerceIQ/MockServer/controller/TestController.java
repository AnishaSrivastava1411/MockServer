package com.commerceIQ.MockServer.controller;
import org.apache.logging.log4j.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.tools.sjavac.Log;

@RestController
public class TestController {
	
	
	private static final Logger logger = LogManager.getLogger(TestController.class);
	
	
@PostMapping("/get")

public String get(@RequestBody String data) {
	Log.info("inside get method {}"+data);
	return data;
}

}






//
//JSONObject request  =  new JSONObject(data);
//request.put("id", ++count);
//JSONObject response  = new JSONObject();
//response.putOpt("ResponseCode", "200");
//response.putOpt("ResponseMessage", "Success");
//response.putOpt("ResponseData", request);