package com.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.base.BaseClassForSelenium;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
public class SeleniumReporting extends BaseClassForSelenium {
	
	public static void getReports(String jsonFile) throws FileNotFoundException, IOException {
		File file = new File(System.getProperty("user.dir")+getPropertyFileValue("JVMReportForSelenium"));
		Configuration configuration = new Configuration(file, "Selenium Automation");
		configuration.addClassifications("Browser", "Chrome");
		configuration.addClassifications("Version", "103");
		configuration.addClassifications("OS", "WIN10");
		List<String> jsonFiles =new ArrayList<String>();
		jsonFiles.add(jsonFile);
		log.info("JSONFile added successfully");
		ReportBuilder builder= new ReportBuilder(jsonFiles, configuration);
		builder.generateReports();
		
}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
