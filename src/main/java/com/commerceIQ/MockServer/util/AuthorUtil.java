package com.commerceIQ.MockServer.util;

import java.io.FileReader;

import java.io.FileWriter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("prototype")
public class AuthorUtil {
	
	@Value("${filePath}")
	public String filePath;
	
	
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
			return "{}";
		}
		
	}
	
	public String getAuthorData() {
		try {
		
			JSONObject loadAllData;
			String data = readDataFromFile();
			if(data.length()==0)
				loadAllData = new JSONObject();
			else
				loadAllData = new JSONObject(data);
		
			JSONArray author = new JSONArray(loadAllData.optString("authors", "[]"));
		
			return author.toString();
			
		}catch (Exception e) {
			e.printStackTrace();
			return "[]";
		}
		
	}
	
	public int getlastIndex(){
		try {
			return new JSONArray(getAuthorData()).length();
			
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
			
		}
		
	}
	
	public String getAuthorData(int index) {
		try {
			JSONArray arr = new JSONArray(getAuthorData());
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
			return "Not Found";
	}

}
	public boolean removeAuthorData(int index)
	{
		try {
			JSONArray arr = new JSONArray(getAuthorData());
			for(int i =0;i<arr.length();i++)
			{
				JSONObject temp = new JSONObject(arr.get(i));
				if(temp.optInt("id")==index) {
					arr.remove(i);
				return	storeAuthorData(arr.toString());
					
				}
									
			}
			return false;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean storeAuthorData(String data)
	{
		try {
			JSONObject alldatajson = new JSONObject(readDataFromFile());
			System.out.println(alldatajson.toString());
			alldatajson.remove("authors");
			alldatajson.putOpt("authors", new JSONArray(data));
			
		     FileWriter file = new FileWriter(filePath);
	         file.write(alldatajson.toString());
	         file.close();
		return true;
		
	}catch(Exception e) {
		e.printStackTrace();
		return false;
	}
}
	
	public boolean updateAuthorData(int index, JSONObject updateData) {
		
		
		try {
			JSONArray arr = new JSONArray(getAuthorData());
			for(int i =0;i<arr.length();i++)
			{
				JSONObject temp = new JSONObject(arr.get(i));
				if(temp.optInt("id")==index) {
					arr.remove(i);
					arr.put(updateData);
					return storeAuthorData(arr.toString());
					
					
				}
									
			}
			return false;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public boolean addData(String data) {
		try {
		 JSONArray arr = new JSONArray(getAuthorData());
		 System.out.println("159"+arr.toString());
		 arr.put(new JSONObject(data));
		return  storeAuthorData(arr.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
