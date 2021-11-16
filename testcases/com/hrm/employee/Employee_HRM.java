package com.hrm.employee;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.hrm.AddEmpPO;
import pageObjects.hrm.DashBoardPO;
import pageObjects.hrm.EmpInfoPO;
import pageObjects.hrm.EmpListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.PageGeneratorManager;
import reportConfig.ExtentTestManager;
import utilities.DataUtil;

public class Employee_HRM extends BaseTest {
	WebDriver driver;
	String adminUsername, adminPassword,empUserName, empPassword, firstName,lastName, fullName;
	String avatarFilePath = GlobalConstants.UPLOAD_FOLDER_PATH + "Image_avatar.jpg";
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		fakeData = DataUtil.getData();
		
		adminUsername = "Admin";
		adminPassword = "admin123";
		empUserName = fakeData.getEmailAddress();
		empPassword = fakeData.getPassword();
		firstName = fakeData.getFirstName();
		lastName = fakeData.getLastName();
		fullName = firstName +" " + lastName;
		driver = getDriverBrowsers(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		dashBoardPage = loginPage.loginToTheSystem(driver, "txtUsername","txtPassword","btnLogin",adminUsername,adminPassword);
	}

	@Test
	public void TC_01_Add_New_Employee(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_01_Register_To_System");
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 01: Open Menu Header: 'PIM' and Sub Menu: 'Employee List'");
		dashBoardPage.clickOnMenuAndSubMenuByName(driver,"PIM","Employee List");
		empListPage = PageGeneratorManager.getEmpListPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 02: Click to 'Add' Button");
		empListPage.isJQueryAjaxLoadedSuccess(driver);
		empListPage.clickOnButtonByID(driver, "btnAdd");
		addEmpPage = PageGeneratorManager.getAddEmpPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 03: Input to First Name: " + firstName);
		addEmpPage.inputTextBoxByID(driver, firstName, "firstName");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 04: Input to Last Name: " + lastName);
		addEmpPage.inputTextBoxByID(driver, lastName, "lastName");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 05: Click to 'Create Login Details' Checkbox");
		addEmpPage.clickOnButtonByID(driver, "chkLogin");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 06: Input to User Name: " + empUserName);
		addEmpPage.inputTextBoxByID(driver, empUserName, "user_name");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 07: Input to Password: " + empPassword);
		addEmpPage.inputTextBoxByID(driver, empPassword, "user_password");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 08: Input to Confirm Password: " + empPassword);
		addEmpPage.inputTextBoxByID(driver, empPassword, "re_password");
	
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 09: Select status is 'Enable'");
		addEmpPage.selectDropdownByIDAndText(driver, "status", "Enabled");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 10: Click to 'Save' Button");
		addEmpPage.clickOnButtonByID(driver, "btnSave");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 11: Click to SubMenu: 'Employee List'");
		addEmpPage.clickOnMenuAndSubMenuByName(driver,"PIM","Employee List");
		empListPage = PageGeneratorManager.getEmpListPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 12: Input to Employee Name: "+  firstName);
		empListPage.isJQueryAjaxLoadedSuccess(driver);
		empListPage.inputTextBoxByID(driver, fullName, "empsearch_employee_name_empName");
		empListPage.isJQueryAjaxLoadedSuccess(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 13: Click to 'Search' Button");
		empListPage.clickOnButtonByID(driver, "searchBtn");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 14: Verify First Name in Table: " + firstName);
		verifyEquals(empListPage.getValueByTableIdAndColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), firstName);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 15: Verify Last Name in Table: "+ lastName);
		verifyEquals(empListPage.getValueByTableIdAndColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), lastName);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 16: Logout from the system");
		loginPage = empListPage.logoutFromTheSystem(driver,"Logout");
		ExtentTestManager.endTest();
	}
	
	@Test
	public void TC_02_Upload_Employee(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_01_Register_To_System");
		ExtentTestManager.getTest().log(LogStatus.INFO, "02_Add_New_Employee - Step 01: Login with user name: "+empUserName +" and password "+empPassword);
		dashBoardPage = loginPage.loginToTheSystem(driver, "txtUsername","txtPassword","btnLogin",empUserName,empPassword);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "02_Add_New_Employee - Step 02: Open Menu Header: 'My Info'");
		dashBoardPage.clickOnMenuByName(driver, "My Info");
		empInfoPage = PageGeneratorManager.getEmpInfoPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "02_Add_New_Employee - Step 03: Click on image link to change photo");
		empInfoPage.clickOnImageLink();
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "02_Add_New_Employee - Step 04: Upload new Avatar image");
		empInfoPage.uploadImage(driver, avatarFilePath);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "02_Add_New_Employee - Step 05: Click on Upload Button");
		empInfoPage.clickOnButtonByID(driver, "btnSave");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "02_Add_New_Employee - Step 06: Verify success message upload is displayed");
		verifyTrue(empInfoPage.isUploadAvatarSuccessMessageDisplayed());
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "02_Add_New_Employee - Step 06: Verify new Avatar image is displayed");
		verifyTrue(empInfoPage.isNewAvatarImageDisplayed());
		ExtentTestManager.endTest();
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	LoginPO loginPage;
	EmpListPO empListPage;
	EmpInfoPO empInfoPage;
	DashBoardPO dashBoardPage;
	AddEmpPO addEmpPage;
	DataUtil fakeData;

}
