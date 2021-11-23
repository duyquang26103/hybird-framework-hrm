package commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.hrm.DashBoardPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.PageGeneratorManager;
import pageUIs.BasePageUI;
import pageUIs.EmpInfoPageUI;

public class BasePage {

	public static BasePage getBasePage() {
		return new BasePage();
	}

	public void getURL(WebDriver driver, String name) {
		driver.get(name);
	}

	public String getTitle(WebDriver driver, String name) {
		return driver.getTitle();
	}

	public String getCurrentURL(WebDriver driver, String name) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver, String name) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitAlertPresence(WebDriver driver) {
		WebDriverWait explicit = new WebDriverWait(driver, longTimeout);
		return explicit.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitAlertPresence(driver).accept();

	}

	public void cancelAlert(WebDriver driver) {
		waitAlertPresence(driver).dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		return waitAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String textAlert) {
		waitAlertPresence(driver).sendKeys(textAlert);
	}

	public void switchToWindownByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String id : allWindows) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				break;
			}
		}

	}

	public void switchToWindownByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String id : allWindows) {
			driver.switchTo().window(id);
			String titleWindow = driver.getTitle();
			if (titleWindow.equals(expectedTitle)) {
				break;
			}
		}

	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String id : allWindows) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}

	}

	public Set<Cookie> getAllCookie(WebDriver driver) {
		Set<Cookie> allCookies = driver.manage().getCookies();
		return allCookies;
	}

	public void setAllCookie(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
		allCookies = driver.manage().getCookies();
	}

	protected WebElement getWebElement(WebDriver driver, String xpathlocator) {
		return driver.findElement(getXpath(xpathlocator));
	}

	private List<WebElement> getWebElements(WebDriver driver, String xpathlocator) {
		return driver.findElements(getXpath(xpathlocator));
	}

	protected By getXpath(String xpathlocator) {
		return By.xpath(xpathlocator);
	}

	public String getDymamicLocator(String locator, String... params) {
		return String.format(locator, (Object[]) params);
	}

	public void clickToElement(WebDriver driver, String xpathlocator) {
		getWebElement(driver, xpathlocator).click();
	}

	public void clickToElement(WebDriver driver, String xpathlocator, String... params) {
		getWebElement(driver, getDymamicLocator(xpathlocator, params)).click();
	}

	public void sendKeyToElement(WebDriver driver, String xpathlocator, String textValue) {
		WebElement element = getWebElement(driver, xpathlocator);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendKeyToElement(WebDriver driver, String xpathlocator, String textValue, String... params) {
		xpathlocator = getDymamicLocator(xpathlocator, params);
		WebElement element = getWebElement(driver, xpathlocator);
		element.clear();
		element.sendKeys(textValue);
	}

	protected String getElementText(WebDriver driver, String xpathlocator) {
		return getWebElement(driver, xpathlocator).getText().trim();
	}

	protected String getElementText(WebDriver driver, String xpathlocator, String... params) {
		return getWebElement(driver, getDymamicLocator(xpathlocator, params)).getText();
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String xpathlocator, String textItem) {
		Select select = new Select(getWebElement(driver, xpathlocator));
		select.selectByValue(textItem);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String xpathlocator, String textItem, String...params) {
		Select select = new Select(getWebElement(driver, getDymamicLocator(xpathlocator, params)));
		select.selectByValue(textItem);
	}

	public void selectItemInDefaultDropdownByText(WebDriver driver, String xpathlocator, String textItem) {
		Select select = new Select(getWebElement(driver, xpathlocator));
		select.selectByVisibleText(textItem);
	}

	public void selectItemInDefaultDropdownByText(WebDriver driver, String xpathlocator, String textItem,
			String... params) {
		Select select = new Select(getWebElement(driver, getDymamicLocator(xpathlocator, params)));
		select.selectByVisibleText(textItem);
	}

	public String getSelectItemDefaultDropdown(WebDriver driver, String xpathlocator) {
		Select select = new Select(getWebElement(driver, xpathlocator));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getSelectItemDefaultDropdown(WebDriver driver, String xpathlocator,String...params) {
		Select select = new Select(getWebElement(driver, getDymamicLocator(xpathlocator, params)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMutiple(WebDriver driver, String xpathlocator) {
		Select select = new Select(getWebElement(driver, xpathlocator));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator,
			String expectedItem) {
		getWebElement(driver, parentLocator);
		sleepInSecond(1);

		WebDriverWait explicit = new WebDriverWait(driver, longTimeout);
		List<WebElement> ListItems = explicit
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getXpath(childItemLocator)));

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
	

	public String getAttributeValue(WebDriver driver, String xpathlocator, String attributeName) {
		return getWebElement(driver, xpathlocator).getAttribute(attributeName);
	}
	
	public String getAttributeValue(WebDriver driver, String xpathlocator, String attributeName, String...params) {
		return getWebElement(driver, getDymamicLocator(xpathlocator, params)).getAttribute(attributeName);
	}

	public String getCssValue(WebDriver driver, String xpathlocator, String propertyName) {
		return getWebElement(driver, xpathlocator).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementSize(WebDriver driver, String xpathlocator) {
		return getWebElements(driver, xpathlocator).size();
	}
	
	public int getElementSize(WebDriver driver, String xpathlocator, String...params) {
		return getWebElements(driver, getDymamicLocator(xpathlocator, params)).size();
	}

	public void checkTheCheckboxOrRadio(WebDriver driver, String xpathlocator) {
		WebElement element = getWebElement(driver, xpathlocator);
		if (!element.isEnabled()) {
			element.click();
		}
	}

	public void uncheckTheCheckbox(WebDriver driver, String xpathlocator) {
		WebElement element = getWebElement(driver, xpathlocator);
		if (element.isEnabled()) {
			element.click();
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String xpathlocator) {
		return getWebElement(driver, xpathlocator).isDisplayed();
	}

	public boolean isControlDisplayed(WebDriver driver, String xpathlocator, String... params) {
		return getWebElement(driver, getDymamicLocator(xpathlocator, params)).isDisplayed();
	}
	
	public boolean isControlEnabled(WebDriver driver, String xpathlocator, String... params) {
		return getWebElement(driver, getDymamicLocator(xpathlocator, params)).isEnabled();
	}

	public boolean isControlUnDisplayed(WebDriver driver, String xpathlocator) {
		overrideGlobalTime(driver, shortTimeout);
		List<WebElement> elements = getWebElements(driver, xpathlocator);
		overrideGlobalTime(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void overrideGlobalTime(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isControlSelected(WebDriver driver, String xpathlocator) {
		return getWebElement(driver, xpathlocator).isSelected();
	}
	
	public boolean isControlSelected(WebDriver driver, String xpathlocator, String...params) {
		return getWebElement(driver, getDymamicLocator(xpathlocator, params)).isSelected();
	}

	public boolean isControlEnabled(WebDriver driver, String xpathlocator) {
		return getWebElement(driver, xpathlocator).isEnabled();
	}

	public void switchToIframeFrame(WebDriver driver, String xpathlocator) {
		driver.switchTo().frame(getWebElement(driver, xpathlocator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void moveToElement(WebDriver driver, String xpathlocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathlocator)).perform();
	}
	
	public void enterPush(WebDriver driver) {
		Actions action = new Actions(driver);
		action.keyDown(Keys.ENTER).perform();
		action.keyUp(Keys.ENTER).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String xpathlocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathlocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathlocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathlocator));
	}

	public void scrollToElement(WebDriver driver, String xpathlocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathlocator));
	}

	public void scrollToElement(WebDriver driver, String xpathlocator, String... params) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
				getWebElement(driver, getDymamicLocator(xpathlocator, params)));
	}

	public void sendkeyToElementByJS(WebDriver driver, String xpathlocator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')",
				getWebElement(driver, xpathlocator));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathlocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, xpathlocator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery!=null) && (jQuery.active == 0);");
			}
		};

		return explicitWait.until(jQueryLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String xpathlocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, xpathlocator));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathlocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, xpathlocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String xpathlocator) {
		WebDriverWait explicit = new WebDriverWait(driver, longTimeout);
		explicit.until(ExpectedConditions.visibilityOfElementLocated(getXpath(xpathlocator)));
	}

	public void waitForElementVisible(WebDriver driver, String xpathlocator, String... params) {
		WebDriverWait explicit = new WebDriverWait(driver, longTimeout);
		explicit.until(
				ExpectedConditions.visibilityOfElementLocated(getXpath(getDymamicLocator(xpathlocator, params))));
	}

	public void waitForAllsElementVisible(WebDriver driver, String xpathlocator) {
		WebDriverWait explicit = new WebDriverWait(driver, longTimeout);
		explicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getXpath(xpathlocator)));
	}

	public void waitForElementInvisible(WebDriver driver, String xpathlocator) {
		WebDriverWait explicit = new WebDriverWait(driver, longTimeout);
		explicit.until(ExpectedConditions.invisibilityOfElementLocated(getXpath(xpathlocator)));
	}

	public void waitForAllsElementInvisible(WebDriver driver, String xpathlocator) {
		WebDriverWait explicit = new WebDriverWait(driver, shortTimeout);
		explicit.until(ExpectedConditions.invisibilityOfAllElements(getWebElements(driver, xpathlocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathlocator) {
		WebDriverWait explicit = new WebDriverWait(driver, longTimeout);
		explicit.until(ExpectedConditions.elementToBeClickable(getXpath(xpathlocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathlocator, String... params) {
		WebDriverWait explicit = new WebDriverWait(driver, longTimeout);
		explicit.until(ExpectedConditions.elementToBeClickable(getXpath(getDymamicLocator(xpathlocator, params))));
	}

	public void waitForElementSelected(WebDriver driver, String xpathlocator) {
		WebDriverWait explicit = new WebDriverWait(driver, longTimeout);
		explicit.until(ExpectedConditions.elementToBeSelected(getXpath(getDymamicLocator(xpathlocator))));
	}

	public void inputTextBoxByID(WebDriver driver, String value, String idName) {
		waitForElementVisible(driver, BasePageUI.INPUT_TEXTBOX_BY_ID, idName);
		sendKeyToElement(driver, BasePageUI.INPUT_TEXTBOX_BY_ID, value, idName);
	}
	
	public boolean isTextBoxByIDEnabled(WebDriver driver, String idName) {
		waitForElementVisible(driver, BasePageUI.INPUT_TEXTBOX_BY_ID, idName);
		return isControlEnabled(driver, BasePageUI.INPUT_TEXTBOX_BY_ID, idName);
	}
	
	public boolean isRadioButtonByIDEnabled(WebDriver driver, String idName) {
		waitForElementVisible(driver, BasePageUI.RADIO_BUTTON_BY_ID, idName);
		return isControlEnabled(driver, BasePageUI.RADIO_BUTTON_BY_ID, idName);
	}
	
	public boolean isSelectDropdownByIDEnabled(WebDriver driver, String idName) {
		waitForElementVisible(driver, BasePageUI.SELECT_DROPDOWN_BY_ID, idName);
		return isControlEnabled(driver, BasePageUI.SELECT_DROPDOWN_BY_ID, idName);
	}
	
	public boolean isSelectCheckboxByIDEnabled(WebDriver driver, String idName) {
		waitForElementVisible(driver, BasePageUI.INPUT_CHECKBOX_BY_ID, idName);
		return isControlEnabled(driver, BasePageUI.INPUT_CHECKBOX_BY_ID, idName);
	}
	
	public String getValueTextBoxByID(WebDriver driver, String idName, String value) {
		waitForElementVisible(driver, BasePageUI.INPUT_TEXTBOX_BY_ID, idName);
		return getAttributeValue(driver, BasePageUI.INPUT_TEXTBOX_BY_ID,value, idName) ;
	}
	
	public String getValueSelectedDropdownByID(WebDriver driver, String idName) {
		waitForElementVisible(driver, BasePageUI.SELECT_DROPDOWN_BY_ID, idName);
		return getSelectItemDefaultDropdown(driver, BasePageUI.SELECT_DROPDOWN_BY_ID, idName) ;
	}
	
	public void clickOnButtonByID(WebDriver driver, String buttonName) {
		waitForElementVisible(driver, BasePageUI.BUTTON_BY_ID, buttonName);
		clickToElement(driver, BasePageUI.BUTTON_BY_ID, buttonName);
	}
	
	public void clickOnRadioButtonByID(WebDriver driver, String buttonName) {
		waitForElementVisible(driver, BasePageUI.RADIO_BUTTON_BY_ID, buttonName);
		clickToElement(driver, BasePageUI.RADIO_BUTTON_BY_ID, buttonName);
	}

	public void selectDropdownByIDAndText(WebDriver driver, String dropDownID, String textItem) {
		waitForElementVisible(driver, BasePageUI.SELECT_DROPDOWN_BY_ID, dropDownID);
		selectItemInDefaultDropdownByText(driver, BasePageUI.SELECT_DROPDOWN_BY_ID, textItem, dropDownID);
	}
	
	public void selectDropdownByIDAndValue(WebDriver driver, String dropDownID, String value) {
		waitForElementVisible(driver, BasePageUI.SELECT_DROPDOWN_BY_ID, dropDownID);
		selectItemInCustomDropdown(driver, BasePageUI.SELECT_DROPDOWN_BY_ID, value, dropDownID);
	}
	
	public boolean isSuccessMessageDisplayed(WebDriver driver, String textName) {
		waitForElementVisible(driver, BasePageUI.SUCCESS_MESSAGE_BY_TEXT,textName);
		return isControlDisplayed(driver, BasePageUI.SUCCESS_MESSAGE_BY_TEXT, textName);
	}
	
	public boolean isRadioButtonSelectedByID(WebDriver driver, String idName) {
		waitForElementVisible(driver, BasePageUI.RADIO_BUTTON_BY_ID,idName);
		return isControlSelected(driver, BasePageUI.RADIO_BUTTON_BY_ID, idName);
	}

	public DashBoardPO loginToTheSystem(WebDriver driver, String userNameID, String passwordID, String buttonID,String userName, String password) {
		waitForElementVisible(driver, BasePageUI.INPUT_TEXTBOX_BY_ID, userNameID);
		sendKeyToElement(driver, BasePageUI.INPUT_TEXTBOX_BY_ID, userName, userNameID);
		waitForElementVisible(driver, BasePageUI.INPUT_TEXTBOX_BY_ID, passwordID);
		sendKeyToElement(driver, BasePageUI.INPUT_TEXTBOX_BY_ID, password, passwordID);
		clickToElement(driver, BasePageUI.BUTTON_BY_ID, buttonID);
		return PageGeneratorManager.getDashBoardPage(driver);
	}
	
	public LoginPO logoutFromTheSystem(WebDriver driver, String subWelComeName) {
		waitForElementVisible(driver, BasePageUI.WELCOME_ACCOUNT);
		clickToElement(driver, BasePageUI.WELCOME_ACCOUNT);
		waitForElementVisible(driver, BasePageUI.WELCOME_ACCOUNT_SUB_TITLE_BY_NAME, subWelComeName);
		clickToElement(driver, BasePageUI.WELCOME_ACCOUNT_SUB_TITLE_BY_NAME, subWelComeName);
		return PageGeneratorManager.getLoginPage(driver);
	}
	
	public void clickOnMenuByName(WebDriver driver, String menuName) {
		waitForElementVisible(driver, BasePageUI.MENU_BY_NAME, menuName);
		clickToElement(driver, BasePageUI.MENU_BY_NAME, menuName);
	}

	public void clickOnMenuAndSubMenuByName(WebDriver driver, String menuName, String subMenuName) {
		waitForElementVisible(driver, BasePageUI.MENU_BY_NAME, menuName);
		clickToElement(driver, BasePageUI.MENU_BY_NAME, menuName);
		waitForElementVisible(driver, BasePageUI.MENU_BY_NAME, subMenuName);
		clickToElement(driver, BasePageUI.MENU_BY_NAME, subMenuName);
	}
	
	public String getValueByTableIdAndColumnNameAndRowIndex(WebDriver driver, String tableID, String headerName, String rowIndex) {
		waitForElementVisible(driver, BasePageUI.TABLE_BY_ID_AND_ROW_HEADER_BY_NAME, tableID, headerName);
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_BY_ID_AND_ROW_HEADER_BY_NAME, tableID, headerName) + 1;
		return getElementText(driver, BasePageUI.TABLE_BY_ID_AND_ROW_BY_INDEX_COLUMN_BY_INDEX, tableID,rowIndex,String.valueOf(columnIndex));
	}
	
	public String getValueByTableIdAndColumnNameAndRowIndexNoHeader(WebDriver driver, String tableID, String headerName, String rowIndex) {
		waitForElementVisible(driver, BasePageUI.TABLE_BY_ID_AND_ROW_HEADER_BY_NAME, tableID, headerName);
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_BY_ID_AND_ROW_HEADER_BY_NAME, tableID, headerName) + 1;
		return getElementText(driver, BasePageUI.TABLE_BY_ID_AND_ROW_BY_INDEX_COLUMN_BY_INDEX, tableID,rowIndex,String.valueOf(columnIndex));
	}
	
	public void clickValueByTableIdAndColumnNameAndRowIndex(WebDriver driver, String tableID, String headerName, String rowIndex) {
		waitForElementVisible(driver, BasePageUI.TABLE_BY_ID_AND_ROW_HEADER_BY_NAME, tableID, headerName);
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_BY_ID_AND_ROW_HEADER_BY_NAME, tableID, headerName) + 1;
		clickToElement(driver, BasePageUI.TABLE_TO_CLICK_BY_ID_AND_ROW_BY_INDEX_COLUMN_BY_INDEX, tableID, rowIndex,String.valueOf(columnIndex));
	}
	
	public void uploadImage(WebDriver driver, String filePath) {
		getWebElement(driver, BasePageUI.UPLOAD_FILE).sendKeys(filePath);;
	}

	

	public List<Integer> addElementToList(WebDriver driver, String listItem) {
		List<WebElement> AllItems = getWebElements(driver, listItem);
		List<Integer> list = new ArrayList<Integer>();
		;
		for (int i = 0; i < AllItems.size(); i++) {
			String textItem = AllItems.get(i).getText().trim().replace("$", "").replace(",", "").replace(".", "");
			int price = Integer.parseInt(textItem);
			list.add(price);
		}
		return list;
	}

	public boolean checkElementSortLowToHigh(WebDriver driver, String listItem) {
		List<Integer> list = addElementToList(driver, listItem);
		Integer[] arr = (Integer[]) list.toArray(new Integer[list.size()]);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] <= arr[i + 1]) {
				return true;
			}
		}
		return false;
	}

	public boolean checkElementSortHighToLow(WebDriver driver, String listItem) {
		List<Integer> list = addElementToList(driver, listItem);
		Integer[] arr = (Integer[]) list.toArray(new Integer[list.size()]);
		for (int i = 0; i < arr.length + 1; i++) {
			if (arr[i] >= arr[i + 1]) {
				return true;
			}
		}
		return false;
	}

	protected long shortTimeout = GlobalConstants.SHORT_TIME_OUT;

	protected long longTimeout = GlobalConstants.LONG_TIME_OUT;

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
