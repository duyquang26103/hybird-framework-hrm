package com.hrm.employee;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import commons.BaseTest;
import pageObjects.hrm.AddEmpPO;
import pageObjects.hrm.DashBoardPO;
import pageObjects.hrm.EmpInfoPO;
import pageObjects.hrm.EmpListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.PageGeneratorManager;
import reportConfig.ExtentTestManager;

public class Employee_HRM extends BaseTest {
	WebDriver driver;
	String adminUsername, adminPassword,empUserName, empPassword, firstName,lastName;
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		adminUsername = "Admin";
		adminPassword = "admin123";
		empUserName = RandomEmail();
		empPassword = "QAautomation@";
		firstName = "Draken";
		lastName = "Shiliki";
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
		empListPage.inputTextBoxByID(driver, firstName, "empsearch_employee_name_empName");
		empListPage.isJQueryAjaxLoadedSuccess(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 13: Click to 'Search' Button");
		empListPage.clickOnButtonByID(driver, "searchBtn");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 14: Verify First Name in Table: " + firstName);
		verifyEquals(empListPage.getValueByTableIdAndColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), firstName);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 15: Verify Last Name in Table: "+ lastName);
		verifyEquals(empListPage.getValueByTableIdAndColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), lastName);

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

}
