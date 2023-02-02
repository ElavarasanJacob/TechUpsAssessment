package com.stepDefination.API;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.base.BaseClassForAPI;
import com.endpoints.Endpoints;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APICallsWithMultipleEnpointsStep extends BaseClassForAPI {
	 static Response response;
	 Map<String,String> apiServices= new HashMap<>();

	@Given("^Retrieve the Open Weather Map end point$")
	public void retrieve_the_Open_Weather_Map_end_point() throws IOException {
		String openweathermap = Endpoints.OPENWEATHERMAP;
		addHeader("Accept", "application/json");
		apiServices.put("openweathermap", openweathermap);
	}

	@When("^Hit \"(.*)\" end point to fetch the data in Open Weather Map using (.*) for \"(.*)\" and \"(.*)\"$")
	public void hit_The_Endpoint_To_Fetch_The_Data_In_Open_Weather_Map_Using(String httpMethod,String key,
			String keyValue,String appid) {
		queryParam(key, keyValue);
		queryParam("appid", appid);
		response = requestType(httpMethod, apiServices.get("openweathermap"));
	} 
	
	@When("^Hit \"(.*)\" end point to fetch the data in Open Weather Map for \"(.*)\", \"(.*)\" and \"(.*)\"$")
	public void hit_The_Endpoint_To_Fetch_The_Data_In_Open_Weather_Map(String httpMethod,String lat,String lon,String appid) {
		queryParam("lat", lat);
		queryParam("lon", lon);
		queryParam("appid", appid);
		response = requestType(httpMethod, apiServices.get("openweathermap"));
	} 

	@Then("^Get the values of following fields \"(.*)\",\"(.*)\",\"(.*)\",\"(.*)\",\"(.*)\" and \"(.*)\"$")
	public void get_the_values_of_following_fields(String latitude,
			String longitude,String description,String pressure,String country ,String name) throws ParseException {
		String asPrettyString = response.asPrettyString();
		Map<String, String> weatherMap = readTheResponseBodyOfOpenWeatherMap(asPrettyString, latitude, longitude, description, pressure, country, name);
		log.info(weatherMap);
		System.out.println(weatherMap);
		/*
		 * @Description:Using jsonPath fetching the values
		 */ 
//		log.info(longitude +": "+jsonReader(response, "coord.lon"));
//		log.info(latitude +": "+jsonReader(response, "coord.lat"));
//		log.info(description +": "+jsonReader(response, "weather[0].description"));
//		log.info(pressure +": "+jsonReader(response, "main.pressure"));
//		log.info(country +": "+jsonReader(response, "sys.country"));
//		log.info(name +": "+jsonReader(response, "name"));
		
	}

}
