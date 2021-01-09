package com.commerceIQ.MockServer.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.commerceIQ.MockServer.controller.PostsController;

@Component
@Scope("prototype")
public class PostsUtil {

	@Value("${filePath}")
	public String filePath;
	private static final Logger log = LogManager.getLogger(PostsController.class);

	public String readDataFromFile() {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(filePath));
			JSONObject jsonObject = new JSONObject(obj.toString());
			return jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return "{}";
		}

	}

	public String getPostsData() {
		try {

			JSONObject loadAllData;
			String data = readDataFromFile();
			if (data.length() == 0)
				loadAllData = new JSONObject();
			else
				loadAllData = new JSONObject(data);

			JSONArray Posts = new JSONArray(loadAllData.optString("posts", "[]"));

			return Posts.toString();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return "[]";
		}

	}

	public int getlastIndex() {
		try {
			JSONArray arr = new JSONArray(getPostsData());
			JSONObject obj= new JSONObject();
			
			if(arr.length()>0)
				obj = new JSONObject(arr.get(arr.length()-1).toString());
			
			int i = obj.optInt("id", 0)+1;
			
			return i;

		} catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return 0;

		}

	}
public String filterTitleAuthor(String title,String author) {
	try {
		JSONArray arr = new JSONArray(getPostsData());
		for (int i = 0; i < arr.length(); i++) {
			JSONObject temp = new JSONObject(arr.get(i).toString());
			if (temp.optString("title").equals(title)&&temp.optString("author").equals(author))
				return temp.toString();

		}

		return "Not Found";

	} catch (Exception e) {
		e.printStackTrace();
		log.error("error {}", e.getMessage());
		return "Not Found";
	}
}

	public String getPostsData(int index) {
		try {
			JSONArray arr = new JSONArray(getPostsData());
			for (int i = 0; i < arr.length(); i++) {
				JSONObject temp = new JSONObject(arr.get(i).toString());
				if (temp.optInt("id") == index)
					return temp.toString();

			}

			return "Not Found";

		} catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return "Not Found";
		}

	}

	public boolean removePostsData(long index) {
		try {
			JSONArray arr = new JSONArray(getPostsData());
			for (int i = 0; i < arr.length(); i++) {
				JSONObject temp = new JSONObject(arr.get(i).toString());
				if (temp.optInt("id") == (int) index) {
					arr.remove(i);
					return storePostsData(arr.toString());

				}

			}
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return false;
		}
	}

	public boolean storePostsData(String data) {
		try {
			JSONObject alldatajson = new JSONObject(readDataFromFile());
			alldatajson.remove("posts");
			alldatajson.putOpt("posts", new JSONArray(data));

			FileWriter file = new FileWriter(filePath);
			file.write(alldatajson.toString());
			file.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return false;
		}
	}

	public boolean updatePostsData(int index, JSONObject updateData) {

		try {
			JSONArray arr = new JSONArray(getPostsData());
			for (int i = 0; i < arr.length(); i++) {
				JSONObject temp = new JSONObject(arr.get(i).toString());
				if (temp.optInt("id") == index) {
					arr.remove(i);
					arr.put(updateData);
					return storePostsData(arr.toString());

				}

			}
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return false;
		}

	}

	public boolean addData(String data) {
		try {
			JSONArray arr = new JSONArray(getPostsData());
			arr.put(new JSONObject(data));
			return storePostsData(arr.toString());

		} catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return false;
		}

	}
	public String sortAllPost(String sortType) {
		return sort( getPostsData() ,sortType);
	}
	
	
	public static  String  sort(String jsonArr,String sortType) {
		  JSONArray jsonArray = new JSONArray(jsonArr);
	      JSONArray sortedJsonArray = new JSONArray();
	      List list = new ArrayList();
	      for(int i = 0; i < jsonArray.length(); i++) {
	         list.add(jsonArray.getJSONObject(i));
	      }
	         Collections.sort(list, new Comparator() {
	         private final String KEY_NAME = sortType;
	         @Override
	         public int compare(Object o1, Object o2) {
	        	 
	        	 JSONObject a = new JSONObject(o1.toString()), b= new JSONObject(o2.toString());
	            String str1 = new String();
	            String str2 = new String();
	            try {
	               str1 = (String)a.get(KEY_NAME);
	               str2 = (String)b.get(KEY_NAME);
	            } catch(JSONException e) {
	               e.printStackTrace();
	            }
	            return str1.compareTo(str2);
	         }
			
	      });
	      for(int i = 0; i < jsonArray.length(); i++) {
	         sortedJsonArray.put(list.get(i));
	      }
	      return sortedJsonArray.toString();
	        }


}
