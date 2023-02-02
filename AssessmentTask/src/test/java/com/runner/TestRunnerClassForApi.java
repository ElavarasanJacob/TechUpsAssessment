package com.runner;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.base.BaseClassForAPI;
import com.reports.APIReporting;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, strict = true, dryRun = false, plugin = { "pretty",
		"json:jsonReport/api.json" }, features = { "src\\test\\resources\\Features\\Services" }, glue = { "com.stepDefination.API" })
public class TestRunnerClassForApi extends BaseClassForAPI {

	@AfterClass
	public static void afterClass() throws IOException {
		String reports = System.getProperty("user.dir") + getPropertyFileValue("getReportForAPI");
		APIReporting.getReports(reports);
		log.info("API Reports Generated successfully");
		log.info("Execution completed");

	}

}
