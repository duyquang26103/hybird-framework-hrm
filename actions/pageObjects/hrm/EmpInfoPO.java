package pageObjects.hrm;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	

	public void selectCurencyDropdownByName(WebDriver driver, String expectedItem) {
		String parentLocator = EmpInfoPageUI.CURENTCY_DROPDOWN;
		String childItemLocator = EmpInfoPageUI.USD_OPTION_DROPDOWN;
		getWebElement(driver, parentLocator);
		sleepInSecond(1);

		WebDriverWait explicit = new WebDriverWait(driver, longTimeout);
		List<WebElement> ListItems = explicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getXpath(childItemLocator)));

		for (WebElement item : ListItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				break;
			}
		}
	}
	
	public String getValueByTableIdAndColumnNameAndRowIndexOfFirstColumn(String tableID, String headerName, String rowIndex) {
		waitForElementVisible(driver, EmpInfoPageUI.TABLE_BY_ID_AND_ROW_HEADER_BY_NAME, tableID, headerName);
		int columnIndex = getElementSize(driver, EmpInfoPageUI.TABLE_BY_ID_AND_ROW_HEADER_BY_NAME, tableID, headerName);
		return getElementText(driver, EmpInfoPageUI.TABLE_BY_ID_AND_ROW_BY_INDEX_COLUMN_BY_INDEX, tableID,rowIndex,String.valueOf(columnIndex));
	}

	public String getValueByPreTableIdAndColumnNameAndRowIndex(WebDriver driver, String preTableID, String headerName, String rowIndex) {
		waitForElementVisible(driver, EmpInfoPageUI.PRE_TABLE_BY_ID_AND_ROW_HEADER_BY_NAME, preTableID, headerName);
		int columnIndex = getElementSize(driver, EmpInfoPageUI.PRE_TABLE_BY_ID_AND_ROW_HEADER_BY_NAME, preTableID, headerName) + 1;
		return getElementText(driver, EmpInfoPageUI.PRE_TABLE_BY_ID_AND_ROW_BY_INDEX_COLUMN_BY_INDEX, preTableID,rowIndex,String.valueOf(columnIndex));
	}

}
