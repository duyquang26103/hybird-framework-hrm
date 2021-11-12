package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.EmpInfoPageUI;

public class EmpInfoPO extends BasePage{
	WebDriver driver;

	public EmpInfoPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnImageLink() {
		waitForElementClickable(driver, EmpInfoPageUI.IMAGE_LINK);
		clickToElement(driver, EmpInfoPageUI.IMAGE_LINK);
		
	}

	public boolean isUploadAvatarSuccessMessageDisplayed() {
		waitForElementVisible(driver, EmpInfoPageUI.UPLOAD_AVATAR_SUCCESS_MESSAGE);
		return isControlDisplayed(driver, EmpInfoPageUI.UPLOAD_AVATAR_SUCCESS_MESSAGE);
	}

	public boolean isNewAvatarImageDisplayed() {
		waitForElementClickable(driver, EmpInfoPageUI.IMAGE_LINK);
		int widthImage = Integer.parseInt(getAttributeValue(driver, EmpInfoPageUI.IMAGE_LINK, "width"));
		int heighImage = Integer.parseInt(getAttributeValue(driver, EmpInfoPageUI.IMAGE_LINK, "height"));
		return (heighImage != 200) || (widthImage != 200);
	}
	

}
