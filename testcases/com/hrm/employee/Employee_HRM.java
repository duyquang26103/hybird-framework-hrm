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
	String adminUsername, adminPassword,empUserName, empPassword, firstName,lastName, fullName ,editFirstName, editLastName,editFullName ;
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
		
		editFirstName =fakeData.getFirstName();
		editLastName = fakeData.getLastName();
		editFullName = editFirstName +" " + editLastName;
		
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
		verifyTrue(empInfoPage.isSuccessMessageDisplayed(driver,"Successfully Uploaded"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "02_Add_New_Employee - Step 06: Verify new Avatar image is displayed");
		verifyTrue(empInfoPage.isNewAvatarImageDisplayed());
		ExtentTestManager.endTest();
	}
	
	@Test
	public void TC_03_Personal_Details(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_03_Personal_Details");
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 01: Open Personal Details menu at My info Menu Header");
		empInfoPage.openInfoMenuByName("Personal Details");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 02: Verify First Name textbox disable");
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "personal_txtEmpFirstName"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 03: Verify Middle Name textbox disable");
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "personal_txtEmpMiddleName"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 04: Verify Last Name textbox disable");
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "personal_txtEmpLastName"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 05: Verify Order ID textbox disable");
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "personal_txtOtherID"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 06: Verify lisence expire date textbox disable");
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "personal_txtLicExpDate"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 07: Verify gender male radio button disable");
		verifyFalse(empInfoPage.isRadioButtonByIDEnabled(driver, "personal_optGender_1"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 08: Verify gender female radio button disable");
		verifyFalse(empInfoPage.isRadioButtonByIDEnabled(driver, "personal_optGender_2"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 09: Verify Marital Status dropdown disable");
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "personal_cmbMarital"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 10: Verify Nationality dropdown disable");
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "personal_cmbNation"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 11: Verify Nick Name textbox disable");
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "personal_txtEmpNickName"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 12: Verify Smoker checkbox disable");
		verifyFalse(empInfoPage.isSelectCheckboxByIDEnabled(driver, "personal_chkSmokeFlag"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 13: Click on Edit button");
		empInfoPage.clickOnButtonByID(driver, "btnSave");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 14: Input new First Name: " + editFirstName);
		empInfoPage.inputTextBoxByID(driver, editFirstName,"personal_txtEmpFirstName");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 15: Input new Last Name: " + editLastName);
		empInfoPage.inputTextBoxByID(driver, editLastName,"personal_txtEmpLastName");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 16: Click on gender male radio button");
		empInfoPage.clickOnRadioButtonByID(driver, "personal_optGender_1");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 17: Select Marital Status is Single");
		empInfoPage.selectDropdownByIDAndText(driver, "personal_cmbMarital", "Single");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 18: Select Nationality is Afghan");
		empInfoPage.selectDropdownByIDAndText(driver, "personal_cmbNation", "Afghan");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 19: Click on Save button");
		empInfoPage.clickOnButtonByID(driver, "btnSave");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 20: Verify success message upload is displayed");
		verifyTrue(empInfoPage.isSuccessMessageDisplayed(driver,"Successfully Saved"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 21: Verify success new first name is displayed: "+ editFirstName);
		verifyEquals(empInfoPage.getValueTextBoxByID(driver,"personal_txtEmpFirstName","value"), editFirstName);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 22: Verify success new first name is displayed: " + editLastName);
		verifyEquals(empInfoPage.getValueTextBoxByID(driver,"personal_txtEmpLastName","value"), editLastName);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 23: Verify gender male is selected");
		verifyTrue(empInfoPage.isRadioButtonSelectedByID(driver,"personal_optGender_1"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 24: Verify marital selected is Single");
		verifyEquals(empInfoPage.getValueSelectedDropdownByID(driver,"personal_cmbMarital"), "Single");

		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 25: Verify success Nation selected is Afghan");
		verifyEquals(empInfoPage.getValueSelectedDropdownByID(driver,"personal_cmbNation"), "Afghan");
		
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
