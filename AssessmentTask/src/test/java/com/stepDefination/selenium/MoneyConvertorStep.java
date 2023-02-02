package com.stepDefination.selenium;

import org.junit.Assert;

import com.base.BaseClassForSelenium;
import com.page.Xe_Currency_Converter_Pojo;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MoneyConvertorStep extends BaseClassForSelenium {
	Xe_Currency_Converter_Pojo page = new Xe_Currency_Converter_Pojo();

	@Given("I should be on xe page")
	public void i_should_be_on_xe_page() {
		log.info("Home Screen landed successfully");

	}

	@When("I should be able to enter the {string} in Amount field")
	public void i_should_be_able_to_enter_the_in_Amount_field(String string) {
		page.enterAmount(string);

	}

	@When("I should be able to select the {string} and {string} from the drop down")
	public void i_should_be_able_to_select_the_and_from_the_drop_down(String string, String string2) throws InterruptedException {

		page.fromDropDown(string).toDropDown(string2);
	}

	@Then("Amount should be converted and get the converted money")
	public void amount_should_be_converted_and_get_the_converted_money() throws InterruptedException {
		String convertedAmount = page.getConvertedAmount();
		log.info("Converted amount is: "+convertedAmount);
		System.out.println(convertedAmount);

	}

}
