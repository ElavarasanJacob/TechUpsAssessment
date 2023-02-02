package com.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClassForSelenium {

	public static WebDriver driver;
	public static Actions action;
	public static Logger log;

	
	@BeforeClass
	public static void loggerinit() {
		log = Logger.getLogger(BaseClassForAPI.class);
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\log4j.properties");
	}

	public void getDriver(String browserType) {
		switch (browserType) {

		case "chromeDriver":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("Chrome browser launched!");
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("Firefox browser launched!");
			break;
		case "edge":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			log.info("Edge browser launched!");
			break;
		default:
			break;
		}
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

	public void launchURL(String url) {
		driver.get(url);
		log.info("Navigated to :" + url);

	}

	public void implicityWaits(int a) {
		driver.manage().timeouts().implicitlyWait(a, TimeUnit.SECONDS);
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public String getText(WebElement element) {
		String text = element.getText();
		log.info("Get text: " + element.getText());
		return text;
	}

	public void type(WebElement element, String text) {
		waitForVisibility(10,element);
		element.sendKeys(text);
		log.info("Entering the value::" + text + "::in::" + element.getText());
	}

	public void quitWindow() {
		driver.quit();
		log.info("All tabs closed successfully");
	}

	public void closeCurrentBrowser() {
		driver.close();
		log.info("Current browser window closed successfully");
	}

	public void click(WebElement element) {
		waitForVisibility(10, element);
		element.click();
		log.info("Clicking on element::" + element.getText());

	}

	public void clear(WebElement element) {
		element.clear();
		log.info("Clear the Element: " + element.getText());
	}

	public void waitForVisibility(int sec, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, sec);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public boolean isDisplayed(WebElement element) {
		waitForVisibility(30, element);
		boolean displayed = element.isDisplayed();
		return displayed;
	}
	
	public void escape() {
		action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		
	}

}
