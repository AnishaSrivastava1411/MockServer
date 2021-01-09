package com.commerceIQ.MockServer.responsemodel;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ResponseModel {
	
	private String responseCode;
	private Object responseData;
	
	public ResponseModel(String responseCode, Object responseData) {
		super();
		this.responseCode = responseCode;
		this.responseData = responseData;
	}
	public ResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public Object getResponseData() {
		return responseData;
	}
	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}
	@Override
	public String toString() {
		return "ResponseModel [responseCode=" + responseCode + ", responseData=" + responseData + "]";
	}
	

}
