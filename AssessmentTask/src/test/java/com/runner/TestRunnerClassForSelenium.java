package com.runner;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.base.BaseClassForSelenium;
import com.reports.SeleniumReporting;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, strict = true, dryRun = false, plugin = { "pretty",
		"json:jsonReport/selenium.json" }, features = { "src\\test\\resources\\Features\\Web" }, glue = { "com.stepDefination.selenium" })
public class TestRunnerClassForSelenium extends BaseClassForSelenium {

	@AfterClass
	public static void afterClass() throws IOException {
		String reports = System.getProperty("user.dir") + getPropertyFileValue("getReportForSelenium");
		SeleniumReporting.getReports(reports);
		log.info("Reports Generated successfully");
		log.info("Execution completed");
	}

	
}
