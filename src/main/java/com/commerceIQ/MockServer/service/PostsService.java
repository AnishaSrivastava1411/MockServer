
package com.commerceIQ.MockServer.service;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Lookup;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import com.commerceIQ.MockServer.requestmodel.PostsRequestModel;
import com.commerceIQ.MockServer.responsemodel.ResponseModel;
import com.commerceIQ.MockServer.util.PostsUtil;


@Component
@Scope("prototype")
public class PostsService {


	private void validateData(PostsRequestModel PostsRequestModel, ResponseModel responseModel) {
		System.out.println(PostsRequestModel.toString());
		
		if (PostsRequestModel.getAuthor() == null || PostsRequestModel.getAuthor().length() == 0) {
			responseModel.setResponseCode("401");
			responseModel.setResponseData("Author is invalid");
		} else if (PostsRequestModel.getTitle() == null || PostsRequestModel.getTitle().length() == 0) {
			responseModel.setResponseCode("401");
			responseModel.setResponseData("Post is invalid");
		} else if (PostsRequestModel.getViews() == null) {
			responseModel.setResponseCode("401");
			responseModel.setResponseData("Views is invalid");
		} else if (PostsRequestModel.getReviews() == null) {
			responseModel.setResponseCode("401");
			responseModel.setResponseData("Reviews is invalid");
		} else {
			responseModel.setResponseCode("200");
			responseModel.setResponseData("Success for validation");
		}

	}

	public void storePostsData(PostsRequestModel postsRequestModel, ResponseModel responseModel) {

	
		validateData(postsRequestModel, responseModel);
		if (responseModel.getResponseCode().equals("200") == false) {
			return;

		}
		PostsUtil objPostsutil = getPostsUtilObject();

		int id = objPostsutil.getlastIndex();
		postsRequestModel.setId((long) id);

		
		JSONObject reqData = new JSONObject();
		reqData.putOpt("title", postsRequestModel.getTitle());
		reqData.putOpt("author", postsRequestModel.getAuthor());
		reqData.putOpt("views", postsRequestModel.getViews());
		reqData.putOpt("reviews", postsRequestModel.getReviews());
		reqData.putOpt("id", postsRequestModel.getId());

		boolean result = objPostsutil.addData(reqData.toString());
		if(result )
		{ 
			responseModel.setResponseCode("200");
			responseModel.setResponseData("Data update success");			
		}
		else
		{
			responseModel.setResponseCode("401");
			responseModel.setResponseData("failure in storing data");
		}

	}

	public void getAllData(ResponseModel resModel) {
		PostsUtil objPostsutil = getPostsUtilObject();
		String getPostsData = objPostsutil.getPostsData();
		resModel.setResponseCode("200");
		resModel.setResponseData(getPostsData);
		
		
	}
	
	
	@Lookup
	PostsUtil getPostsUtilObject() {
		return null;
	}

}