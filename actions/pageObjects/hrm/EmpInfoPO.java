package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.BasePageUI;
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

	public boolean isNewAvatarImageDisplayed() {
		waitForElementClickable(driver, EmpInfoPageUI.IMAGE_LINK);
		int widthImage = Integer.parseInt(getAttributeValue(driver, EmpInfoPageUI.IMAGE_LINK, "width"));
		int heighImage = Integer.parseInt(getAttributeValue(driver, EmpInfoPageUI.IMAGE_LINK, "height"));
		return (heighImage != 200) || (widthImage != 200);
	}
	
	public void openInfoMenuByName(String menuName) {
		waitForElementVisible(driver, EmpInfoPageUI.MENU_BY_NAME, menuName);
		clickToElement(driver, EmpInfoPageUI.MENU_BY_NAME,menuName);
	}
	
	public String getValueByTableIdAndColumnNameAndRowIndexOfFirstColumn(String tableID, String headerName, String rowIndex) {
		waitForElementVisible(driver, EmpInfoPageUI.TABLE_BY_ID_AND_ROW_HEADER_BY_NAME, tableID, headerName);
		int columnIndex = getElementSize(driver, EmpInfoPageUI.TABLE_BY_ID_AND_ROW_HEADER_BY_NAME, tableID, headerName);
		return getElementText(driver, EmpInfoPageUI.TABLE_BY_ID_AND_ROW_BY_INDEX_COLUMN_BY_INDEX, tableID,rowIndex,String.valueOf(columnIndex));
	}

	

}
