package com.commerceIQ.MockServer.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commerceIQ.MockServer.requestmodel.AuthorRequestModel;
import com.commerceIQ.MockServer.responsemodel.ResponseModel;
import com.commerceIQ.MockServer.service.AuthorService;
import com.commerceIQ.MockServer.util.AuthorUtil;

@RestController
public class AuthorController {
	private static final Logger log = LogManager.getLogger(AuthorController.class);

	@Autowired
	private AuthorService authorService;
	private ResponseModel responseModel;

	@PostMapping(value = "/setAuthor")
	public ResponseEntity<Object> setAuthor(@RequestBody AuthorRequestModel authorRequestModel) {

		log.info("inside setAuthor");
		responseModel = getResponseModelObj();
		getAuthorServiceObj().storeAuthorData(authorRequestModel, responseModel);
		JSONObject res = new JSONObject();
		res.putOpt("ResponseCode", responseModel.getResponseCode());
		res.putOpt("ResponseMessage", responseModel.getResponseData());
		log.info("setAuthor completed {}", responseModel.getResponseCode());
		return new ResponseEntity<>(res.toString(), HttpStatus.OK);
	}

	@GetMapping(value = "/getAllAuthorData")
	public ResponseEntity<Object> getAllData() {
		log.info("inside getAllAuthorData");
		responseModel = getResponseModelObj();
		getAuthorServiceObj().getAllData(responseModel);
		JSONArray arr = new JSONArray(responseModel.getResponseData().toString());
		log.info("getAllAuthorData completed {}", responseModel.getResponseCode());
		return new ResponseEntity<>(arr.toString(), HttpStatus.OK);

	}

	@GetMapping(value = "/getAuthor/{author_id}")
	public ResponseEntity<Object> getAuthor(@PathVariable("author_id") int author_id) {
		log.info("inside getAllAuthorData");
		responseModel = getResponseModelObj();
		String curr = getAuthorUtilObject().getAuthorData(author_id);
		log.info("getAuthor completed {}", responseModel.getResponseCode());
		if (curr.length() == 0) {
			JSONObject response = new JSONObject();
			response.putOpt("ResponseCode", "401");
			response.putOpt("Response Message", "Author Id not found");
			curr = response.toString();
		}
		return new ResponseEntity<>(curr, HttpStatus.OK);

	}

	@DeleteMapping("/deleteAuthor/{author_id}")
	public ResponseEntity<Object> deleteAuthor(@PathVariable(value = "author_id") Long authorId) {
		log.info("inside deleteAuthor" + authorId);
		responseModel = getResponseModelObj();
		if (getAuthorUtilObject().removeAuthorData(authorId)) {
			JSONObject response = new JSONObject();
			response.putOpt("ResponseCode", "200");
			response.putOpt("Response Message", "Author Deleted Successfully");
			log.info("getAllAuthorData completed {}", responseModel.getResponseCode());
			return new ResponseEntity<>(response.toString(), HttpStatus.OK);
		} else {
			JSONObject response = new JSONObject();
			response.putOpt("ResponseCode", "401");
			response.putOpt("Response Message", "Author Deleted Unsuccessfully");
			log.info("getAllAuthorData completed {}");
			return new ResponseEntity<>(response.toString(), HttpStatus.OK);

		}
	}

	@PatchMapping("/updateAuthor/{author_id}")
	public ResponseEntity<Object> updateAuthor(@PathVariable(value = "author_id") int authorId,
			@RequestBody AuthorRequestModel authorRequestModel) {
		log.info("inside updateAuthor" + authorId);
		JSONObject reqData = new JSONObject();
		reqData.putOpt("first_Name", authorRequestModel.getFirst_Name());
		reqData.putOpt("last_Name", authorRequestModel.getLast_Name());
		reqData.putOpt("id", authorId);
		reqData.putOpt("posts", authorRequestModel.getPosts());
		responseModel = getResponseModelObj();
		if (getAuthorUtilObject().updateAuthorData(authorId, reqData)) {
			JSONObject response = new JSONObject();
			response.putOpt("ResponseCode", "200");
			response.putOpt("Response Message", "Author Updated Successfully");
			log.info("update Author completed {}", responseModel.getResponseCode());
			return new ResponseEntity<>(response.toString(), HttpStatus.OK);

		} else {
			JSONObject response = new JSONObject();
			response.putOpt("ResponseCode", "401");
			response.putOpt("Response Message", "Author updated Unsuccessfully");
			log.info("update Author completed {}");
			return new ResponseEntity<>(response.toString(), HttpStatus.OK);

		}

	}

	// GET /author?_sort=posts&_order=asc
	@GetMapping("/getSortedAuthor")
	public String sortAuthor(@RequestParam String _sort, @RequestParam String _order) {
		return getAuthorUtilObject().sortAllAuthor(_sort);
	}

	// GET /Author?first_Name=first_Name1&last_Name=last_Name1
	@GetMapping("/getFilterAuthor")
	public String filterAuthor(@RequestParam String first_Name, @RequestParam String last_Name) {
		return getAuthorUtilObject().filterFirstLast(first_Name, last_Name);
	}

	@Lookup
	AuthorService getAuthorServiceObj() {
		return null;

	}

	@Lookup
	AuthorUtil getAuthorUtilObject() {
		return null;
	}

	@Lookup
	ResponseModel getResponseModelObj() {
		return null;
	}
}
