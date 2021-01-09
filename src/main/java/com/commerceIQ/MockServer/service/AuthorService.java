package com.commerceIQ.MockServer.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.commerceIQ.MockServer.requestmodel.AuthorRequestModel;
import com.commerceIQ.MockServer.responsemodel.ResponseModel;
import com.commerceIQ.MockServer.util.AuthorUtil;
import com.google.gson.Gson;

@Component
@Scope("prototype")
public class AuthorService {


	private void validateData(AuthorRequestModel authorRequestModel, ResponseModel responseModel) {
				
		if (authorRequestModel.getFirst_Name() == null || authorRequestModel.getFirst_Name().length() == 0) {
			responseModel.setResponseCode("401");
			responseModel.setResponseData("First Name is invalid");
		} else if (authorRequestModel.getLast_Name() == null || authorRequestModel.getLast_Name().length() == 0) {
			responseModel.setResponseCode("401");
			responseModel.setResponseData("Last Name is invalid");
		} else if (authorRequestModel.getPosts() == null) {
			responseModel.setResponseCode("401");
			responseModel.setResponseData("Post is invalid");
		} else {
			responseModel.setResponseCode("200");
			responseModel.setResponseData("Success for validation");
		}

	}

	public void storeAuthorData(AuthorRequestModel authorRequestModel, ResponseModel responseModel) {

	
		validateData(authorRequestModel, responseModel);
		if (responseModel.getResponseCode().equals("200") == false) {
			return;

		}
		AuthorUtil objAuthorutil = getAuthorUtilObject();

		int id = objAuthorutil.getlastIndex();
		authorRequestModel.setId((long) id);

		
		JSONObject reqData = new JSONObject();
		reqData.putOpt("first_Name", authorRequestModel.getFirst_Name());
		reqData.putOpt("last_Name", authorRequestModel.getLast_Name());
		reqData.putOpt("id", authorRequestModel.getId());
		reqData.putOpt("posts", authorRequestModel.getPosts());

		boolean result = objAuthorutil.addData(reqData.toString());
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
		AuthorUtil objAuthorutil = getAuthorUtilObject();
		String getAuthorData = objAuthorutil.getAuthorData();
		resModel.setResponseCode("200");
		resModel.setResponseData(getAuthorData);
		
		
	}
	
	
	@Lookup
	AuthorUtil getAuthorUtilObject() {
		return null;
	}

}