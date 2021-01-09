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
public class AuthorUtil {

	@Value("${filePath}")
	public String filePath;
	private static final Logger log = LogManager.getLogger(AuthorController.class);

	public String readDataFromFile() {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(filePath));
			System.out.println(obj.toString());
			JSONObject jsonObject = new JSONObject(obj.toString());
			System.out.println(jsonObject.toString());
			return jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return "{}";
		}

	}

	public String getAuthorData() {
		try {

			JSONObject loadAllData;
			String data = readDataFromFile();
			if (data.length() == 0)
				loadAllData = new JSONObject();
			else
				loadAllData = new JSONObject(data);

			JSONArray author = new JSONArray(loadAllData.optString("authors", "[]"));

			return author.toString();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return "[]";
		}

	}

	public int getlastIndex() {
		try {
			JSONArray arr = new JSONArray(getAuthorData());
			JSONObject obj= new JSONObject();
			if(arr.length()>0)
				obj = new JSONObject(arr.get(arr.length()-1).toString());
			int i = obj.optInt("id", 0);
			return i;

		} catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return 0;

		}

	}

	public String getAuthorData(int index) {
		try {
			JSONArray arr = new JSONArray(getAuthorData());
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

	public boolean removeAuthorData(long index) {
		try {
			JSONArray arr = new JSONArray(getAuthorData());
			for (int i = 0; i < arr.length(); i++) {
				log.info("removeUtilcheck" + arr.get(i).toString());
				JSONObject temp = new JSONObject(arr.get(i).toString());
				log.info(temp.optInt("id"));
				if (temp.optInt("id") == (int) index) {
					log.info("inside if");

					arr.remove(i);
					log.info(arr.toString());
					return storeAuthorData(arr.toString());

				}

			}
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return false;
		}
	}

	public boolean storeAuthorData(String data) {
		try {
			JSONObject alldatajson = new JSONObject(readDataFromFile());
			System.out.println(alldatajson.toString());
			alldatajson.remove("authors");
			alldatajson.putOpt("authors", new JSONArray(data));

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

	public boolean updateAuthorData(int index, JSONObject updateData) {

		try {
			JSONArray arr = new JSONArray(getAuthorData());
			for (int i = 0; i < arr.length(); i++) {
				JSONObject temp = new JSONObject(arr.get(i).toString());
				if (temp.optInt("id") == index) {
					arr.remove(i);
					arr.put(updateData);
					return storeAuthorData(arr.toString());

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
			JSONArray arr = new JSONArray(getAuthorData());
			System.out.println("159" + arr.toString());
			arr.put(new JSONObject(data));
			return storeAuthorData(arr.toString());

		} catch (Exception e) {
			e.printStackTrace();
			log.error("error {}", e.getMessage());
			return false;
		}

	}

}
