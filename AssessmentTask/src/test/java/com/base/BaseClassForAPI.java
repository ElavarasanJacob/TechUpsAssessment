package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class BaseClassForAPI {

	static RequestSpecification reqSpec;
	public static Response response;
	public static Logger log;

	@BeforeClass
	public static void loggerinit() {
		log = Logger.getLogger(BaseClassForAPI.class);
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\Api_log4j.properties");
	}

	public void addHeader(String key, String value) {
		reqSpec = RestAssured.given().header(key, value);
		log.info("Header :: key is:: " + key + "Value is ::" + value);
		log.info("Header Added successfully!");

	}

	public void pathParam(String key, String value) {
		reqSpec = reqSpec.pathParam(key, value);
		log.info("Path parameter key::" + key + "::Value::" + value);
	}

	public void queryParam(String key, String value) {
		reqSpec = reqSpec.queryParam(key, value);
		log.info("Query parameter key::" + key + "::Value::" + value);
	}

	public void addBody(Object payLoad) {
		reqSpec = reqSpec.body(payLoad);
	}

	public void addBody(String payLoad) {
		reqSpec = reqSpec.body(payLoad);
	}

	public void addheaders(List<Header> h) {
		Headers headers = new Headers(h);
		reqSpec = RestAssured.given().headers(headers);
	}

	public Response requestType(String reqType, String endPoint) {
		switch (reqType) {
		case "GET":
			response = reqSpec.get(endPoint);
			log.info("Request type is:: " + reqType + " End point is :: " + endPoint);
			break;
		case "POST":
			response = reqSpec.log().all().post(endPoint);
			log.info("Request type is:: " + reqType + " End point is :: " + endPoint);
			break;
		case "PUT":
			response = reqSpec.log().all().put(endPoint);
			log.info("Request type is:: " + reqType + " End point is :: " + endPoint);
			break;
		case "DELETE":
			response = reqSpec.delete(endPoint);
			log.info("Request type is:: " + reqType + " End point is :: " + endPoint);
			break;

		default:
			break;
		}
		return response;
	}

	public void formData(String formDataKey, String filePath) throws IOException {
		reqSpec = reqSpec.multiPart(formDataKey, new File(filePath));

	}

	public static int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		log.info("Status code is:: " + statusCode);
		return statusCode;

	}

	public static ResponseBody getResBody(Response response) {
		ResponseBody body = response.getBody();
		return body;
	}

	public String resBodyAsString(Response response) {
		String asString = getResBody(response).asString();
		return asString;
	}

	public static String getResBodyAsPrettyString(Response response) {
		String asPrettyString = getResBody(response).asPrettyString();
		return asPrettyString;
	}

	public static String getPropertyFileValue(String key) throws IOException {
		FileInputStream stream = new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
		Properties properties = new Properties();
		properties.load(stream);
		log.info("Config.properties file loaded!");
		Object name = properties.get(key);
		String value = (String) name;
		return value;

	}

	public static void basicAuth(String username, String password) {
		reqSpec = reqSpec.auth().preemptive().basic(username, password);
	}

	public Map<String, String> readTheResponseBodyOfOpenWeatherMap(String responseBody, String lattitude,
			String longitute, String description, String pressure, String country, String name) throws ParseException {
		Map<String, String> mp = new HashMap<>();
		JSONParser js = new JSONParser();
		Object parse = js.parse(responseBody);
		JSONObject getCoord = (JSONObject) parse;
		Object valueCoord = getCoord.get("coord");
		JSONObject getLatLon = (JSONObject) valueCoord;
		Object lat = getLatLon.get(lattitude);
		Object lon = getLatLon.get(longitute);
		Object valueWeather = getCoord.get("weather");
		JSONArray jsonArray = (JSONArray) valueWeather;
		for (Object object : jsonArray) {
			JSONObject getDec = (JSONObject) object;
			Object dec = getDec.get(description);
			mp.put(description, dec.toString());
			log.info("description : " + dec.toString());
		}
		Object main = getCoord.get("main");
		JSONObject getPressure = (JSONObject) main;
		Object pre = getPressure.get(pressure);
		Object sys = getCoord.get("sys");
		JSONObject getCountry = (JSONObject) sys;
		Object country1 = getCountry.get(country);
		Object name1 = getCoord.get(name);
//		Object des = getDec.get("weather[0].description");
		mp.put(lattitude, lat.toString());
		mp.put(longitute, lon.toString());
		mp.put(pressure, pre.toString());
		mp.put(country, country1.toString());
		mp.put(name, name1.toString());
		log.info("lat : " + lat.toString());
		log.info("lon : " + lon.toString());
		log.info("description : " + lat.toString());
		log.info("pressure : " + pre.toString());
		return mp;

	}

	public Object jsonReader(Response response, String jsonData) {
		JsonPath jsonPathObj = response.jsonPath();
		String object = jsonPathObj.get(jsonData).toString();
		return object;
	}

}
