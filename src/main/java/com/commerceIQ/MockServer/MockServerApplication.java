package com.commerceIQ.MockServer;

import java.io.File;
import java.io.FileWriter;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MockServerApplication {


	@Value("${filePath}")
	public String filePath;
	
	public static void main(String[] args) {
		SpringApplication.run(MockServerApplication.class, args);
	}
 
	@PostConstruct
	public void createFile()
	{
		try {
		File f = new File(filePath);
		if(!f.exists()) {
			JSONObject data = new JSONObject();
			data.putOpt("posts",new JSONArray());
			data.putOpt("authors",new JSONArray());
			 FileWriter file = new FileWriter(filePath);
	         file.write(data.toString());
	         file.close();
			
		}
		}
		catch (Exception e) {
			
		}
		

	}
}
