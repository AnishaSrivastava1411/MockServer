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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commerceIQ.MockServer.requestmodel.PostsRequestModel;
import com.commerceIQ.MockServer.responsemodel.ResponseModel;
import com.commerceIQ.MockServer.service.PostsService;
import com.commerceIQ.MockServer.util.PostsUtil;

@RestController
public class PostsController {
	private static final Logger log = LogManager.getLogger(PostsController.class);

	@Autowired
	private PostsService PostsService;
	private ResponseModel responseModel;

	@PostMapping(value = "/setPosts")
	public ResponseEntity<Object> setPosts(@RequestBody PostsRequestModel PostsRequestModel) {
		log.info("inside setPosts");
		responseModel = getResponseModelObj();
		getPostsServiceObj().storePostsData(PostsRequestModel, responseModel);
		JSONObject res = new JSONObject();
		res.putOpt("ResponseCode", responseModel.getResponseCode());
		res.putOpt("ResponseMessage", responseModel.getResponseData());
		log.info("setPosts completed {}", responseModel.getResponseCode());
		return new ResponseEntity<>(res.toString(), HttpStatus.OK);
	}

	@GetMapping(value = "/getAllPostsData")
	public ResponseEntity<Object> getAllData() {
		log.info("inside getAllPostsData");
		responseModel = getResponseModelObj();
		getPostsServiceObj().getAllData(responseModel);
		JSONArray arr = new JSONArray(responseModel.getResponseData().toString());
		log.info("getAllPostsData completed {}", responseModel.getResponseCode());
		return new ResponseEntity<>(arr.toString(), HttpStatus.OK);

	}

	@GetMapping(value = "/getPosts/{posts_id}")
	public ResponseEntity<Object> getPosts(@PathVariable("posts_id") int posts_id) {
		log.info("inside getAllPostsData");
		responseModel = getResponseModelObj();
		String curr = getPostsUtilObject().getPostsData(posts_id);
		log.info("getPosts completed {}", responseModel.getResponseCode());
		if (curr.length() == 0) {
			JSONObject response = new JSONObject();
			response.putOpt("ResponseCode", "401");
			response.putOpt("Response Message", "Posts Id not found");
			curr = response.toString();
		}
		return new ResponseEntity<>(curr, HttpStatus.OK);

	}

	@DeleteMapping("/deletePosts/{posts_id}")
	public ResponseEntity<Object> deletePosts(@PathVariable(value = "posts_id") Long postsId) {
		log.info("inside deletePosts" + postsId);
		responseModel = getResponseModelObj();
		if (getPostsUtilObject().removePostsData(postsId)) {
			JSONObject response = new JSONObject();
			response.putOpt("ResponseCode", "200");
			response.putOpt("Response Message", "Posts Deleted Successfully");
			log.info("getAllPostsData completed {}", responseModel.getResponseCode());
			return new ResponseEntity<>(response.toString(), HttpStatus.OK);
		} else {
			JSONObject response = new JSONObject();
			response.putOpt("ResponseCode", "401");
			response.putOpt("Response Message", "Posts Deleted Unsuccessfully");
			log.info("getAllPostsData completed {}");
			return new ResponseEntity<>(response.toString(), HttpStatus.OK);

		}
	}

	@PutMapping("/updatePosts/{posts_id}")
	public ResponseEntity<Object> updatePosts(@PathVariable(value = "posts_id") int postsId,
			@RequestBody PostsRequestModel PostsRequestModel) {
		log.info("inside updatePosts" + postsId);
		JSONObject reqData = new JSONObject();
		reqData.putOpt("title", PostsRequestModel.getTitle());
		reqData.putOpt("author", PostsRequestModel.getAuthor());
		reqData.putOpt("id", postsId);
		reqData.putOpt("views", PostsRequestModel.getViews());
		reqData.putOpt("reviews", PostsRequestModel.getReviews());
		responseModel = getResponseModelObj();
		if (getPostsUtilObject().updatePostsData(postsId, reqData)) {
			JSONObject response = new JSONObject();
			response.putOpt("ResponseCode", "200");
			response.putOpt("Response Message", "Posts Updated Successfully");
			log.info("update Posts completed {}", responseModel.getResponseCode());
			return new ResponseEntity<>(response.toString(), HttpStatus.OK);

		} else {
			JSONObject response = new JSONObject();
			response.putOpt("ResponseCode", "401");
			response.putOpt("Response Message", "Posts updated Unsuccessfully");
			log.info("update Posts completed {}");
			return new ResponseEntity<>(response.toString(), HttpStatus.OK);

		}

	}
	//GET /posts?_sort=views&_order=asc
	@GetMapping("/getSortedPosts")
	public String sortPost(@RequestParam String _sort,@RequestParam String _order) {
		return getPostsUtilObject().sortAllPost(_sort);
	}

	//GET /posts?title=title1&author=CIQ
	@GetMapping("/getFilterPosts")  
	public String filterPost(@RequestParam String title ,@RequestParam String author) {
		return getPostsUtilObject().filterTitleAuthor(title, author);
	}

	
	@Lookup
	PostsService getPostsServiceObj() {
		return null;

	}

	@Lookup
	PostsUtil getPostsUtilObject() {
		return null;
	}

	@Lookup
	ResponseModel getResponseModelObj() {
		return null;
	}
}
