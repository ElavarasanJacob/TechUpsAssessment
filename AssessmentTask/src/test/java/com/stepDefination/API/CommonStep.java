package com.stepDefination.API;

import org.junit.Assert;

import com.base.BaseClassForAPI;

import cucumber.api.java.en.Then;
import io.restassured.response.Response;

/**
 * @author Elavarasan
 * @Description Common Step of the Status Code for All End Points
 * @Date 01-07-2023
 * 
 */
public class CommonStep extends BaseClassForAPI {
	/**
	 * @Description Verifying response status code for all end points
	 * @Date 01-07-2023
	 * @param expStatusCode
	 */
	@Then("^Verify the status code is (\\d+)$")
	public void verify_The_Status_Code_Is(int expStatusCode) {
		int actualStatusCode = getStatusCode(response);
		System.out.println(actualStatusCode);
		Assert.assertEquals("Verify statusCode", expStatusCode, actualStatusCode);
		log.info("Expected Result is::" + expStatusCode + " Actual Result is::" + actualStatusCode);


	} 
 
}
