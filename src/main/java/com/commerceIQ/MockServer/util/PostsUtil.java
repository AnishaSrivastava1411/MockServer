package com.commerceIQ.MockServer.util;

import java.io.FileReader;

import java.io.FileWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.commerceIQ.MockServer.controller.AuthorController;
@Component
@Scope("prototype")
public class PostsUtil {
	
	@Value("${filePath}")
	public String filePath;
	private static final Logger log = LogManager.getLogger(AuthorController.class);

	
	public String readDataFromFile() {
		try {
			 JSONParser parser = new JSONParser();
	         Object obj = parser.parse(new FileReader(filePath));
	         System.out.println(obj.toString());
	         JSONObject jsonObject = new JSONObject (obj.toString());
	         System.out.println(jsonObject.toString());
	         return jsonObject.toString();
		}
		catch(Exception e ){
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return "{}";
		}
		
	}
	
	public String getPostsData() {
		try {
		
			JSONObject loadAllData;
			String data = readDataFromFile();
			if(data.length()==0)
				loadAllData = new JSONObject();
			else
				loadAllData = new JSONObject(data);
		
			JSONArray Posts = new JSONArray(loadAllData.optString("Posts", "[]"));
		
			return Posts.toString();
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return "[]";
		}
		
	}
	
	public int getlastIndex(){
		try {
			return new JSONArray(getPostsData()).length();
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return 0;
			
		}
		
	}
	
	public String getPostsData(int index) {
		try {
			JSONArray arr = new JSONArray(getPostsData());
			for(int i =0;i<arr.length();i++)
			{
				JSONObject temp = new JSONObject(arr.get(i));
				if(temp.optInt("id")==index)
					return temp.toString();
										
			}
			
			return "Not Found";
			
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return "Not Found";
	}

}
	public boolean removePostsData(int index)
	{
		try {
			JSONArray arr = new JSONArray(getPostsData());
			for(int i =0;i<arr.length();i++)
			{
				JSONObject temp = new JSONObject(arr.get(i));
				if(temp.optInt("id")==index) {
					arr.remove(i);
				return	storePostsData(arr.toString());
					
				}
									
			}
			return false;
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return false;
		}
	}
	public boolean storePostsData(String data)
	{
		try {
			JSONObject alldatajson = new JSONObject(readDataFromFile());
			System.out.println(alldatajson.toString());
			alldatajson.remove("Posts");
			alldatajson.putOpt("Posts", new JSONArray(data));
			
		     FileWriter file = new FileWriter(filePath);
	         file.write(alldatajson.toString());
	         file.close();
		return true;
		
	}catch(Exception e) {
		e.printStackTrace();
		log.error("error {}", e.getMessage());
		return false;
	}
}
	
	public boolean updatePostsData(int index, JSONObject updateData) {
		
		
		try {
			JSONArray arr = new JSONArray(getPostsData());
			for(int i =0;i<arr.length();i++)
			{
				JSONObject temp = new JSONObject(arr.get(i));
				if(temp.optInt("id")==index) {
					arr.remove(i);
					arr.put(updateData);
					return storePostsData(arr.toString());
					
				}
									
			}
			return false;
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return false;
		}
		
		
	}
	
	public boolean addData(String data) {
		try {
		 JSONArray arr = new JSONArray(getPostsData());
		 arr.put(new JSONObject(data));
		return  storePostsData(arr.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return false;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
