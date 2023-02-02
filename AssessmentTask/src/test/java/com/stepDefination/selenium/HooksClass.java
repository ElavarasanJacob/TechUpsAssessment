package com.stepDefination.selenium;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.base.BaseClassForSelenium;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class HooksClass extends BaseClassForSelenium {

	@Before
	public void beforeScenario() throws IOException {
		getDriver(getPropertyFileValue("browserType"));
		launchURL(getPropertyFileValue("Url"));
		maximizeWindow();
		implicityWaits(50);
	}


	@After
	public void afterScenario(Scenario sc) {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		byte[] screenshotAs = screenshot.getScreenshotAs(OutputType.BYTES);
		sc.embed(screenshotAs, "EveryScenario");
		closeCurrentBrowser();
		log.debug("Test execution completed");


	}

}
