package com.commerceIQ.MockServer.controller;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.logging.log4j.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.tools.sjavac.Log;

@RestController
public class TestController {
	
	
	private static final Logger logger = LogManager.getLogger(TestController.class);


	@PostMapping("/get")
	public String get(@RequestBody String data) {
		
		//log.info("inside get method {}"+data);
		return data;
		}
	
//	@GetMapping("/getAll")
//	public List<String> getAll() {
//	///	Log.info("inside getAll method {}");
//		return new ArrayList<String>();
//		}
	
	
	

//	@GetMapping("/get/{id}")
//	public String getIndividual(@PathParam("id" )@PathVariable String id) {
//	//	log.info("inside getIndividual method {}" + id);
//		return id;
//		}

	@GetMapping("/{id}/get")
	public String getOne(@PathParam("id") String id) {
		return id;
	}
	
	
	@PutMapping("/replace")
	String replace(@RequestBody  String data, @PathVariable Long id) {
	//	Log.info("inside replace method {}"+data);
		return data;
		}

	@DeleteMapping("/delete")
	void deleteEmployee(@PathVariable String id) {
		//Log.info("inside delete method {}");
		return;
		}
	}






//
//JSONObject request  =  new JSONObject(data);
//request.put("id", ++count);
//JSONObject response  = new JSONObject();
//response.putOpt("ResponseCode", "200");
//response.putOpt("ResponseMessage", "Success");
//response.putOpt("ResponseData", request);