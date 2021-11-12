package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.DashBoardPageUI;

public class DashBoardPO extends BasePage {
	WebDriver driver;

	public DashBoardPO(WebDriver driver) {
		this.driver = driver;
	}

}
