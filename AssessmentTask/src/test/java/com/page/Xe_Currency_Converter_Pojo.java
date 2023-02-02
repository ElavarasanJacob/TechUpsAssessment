package com.page;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClassForSelenium;

public class Xe_Currency_Converter_Pojo extends BaseClassForSelenium {

	public Xe_Currency_Converter_Pojo() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='amount']")
	private WebElement amountField;
	@FindBy(xpath = "//input[@role='combobox']")
	private WebElement fromField;
	@FindBy(xpath = "//li[@role='option']")
	private List<WebElement> dropDown;
	@FindBy(xpath = "//input[@id='midmarketToCurrency']")
	private WebElement toField;
	@FindBy(xpath = "//button[text()='Convert']")
	private WebElement convertBtn;
	@FindBy(xpath = "//p[@class='result__BigRate-sc-1bsijpp-1 iGrAod']")
	private WebElement convertedMoney;

	public String getConvertedAmount() throws InterruptedException {
		click(convertBtn);
		Thread.sleep(3000);
		escape();
		String res = getText(convertedMoney);
		return res;
	}

	public void enterAmount(String amount) {
		waitForVisibility(20, amountField);
		clear(amountField);
		type(amountField, amount);
	}

	public Xe_Currency_Converter_Pojo fromDropDown(String selectCountry) {
		type(fromField, selectCountry);
		clickDesiredElementFromList(dropDown, selectCountry);
		return this;
	}

	public void toDropDown(String selectCountry) throws InterruptedException {
		type(toField, selectCountry);
		Thread.sleep(1000);
		clickDesiredElementFromList(dropDown, selectCountry);

	}

	public void clickDesiredElementFromList(List<WebElement> element, String selectCountry) {
		for (WebElement webElement : element) {
			if (webElement.getText().contains(selectCountry)) {
				click(webElement);
				break;
			}
		}
	}

}
