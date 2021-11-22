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
	String adminUsername, adminPassword,empUserName, empPassword, firstName,lastName, fullName ,employeeID,editFirstName, editLastName,editFullName ;
	String editStreetName01, editStreetName02, editCity,editState, editZipCode, editHomephone, editMobile, editWorkTelephone, editWorkEmail, editOtherEmail; 
	String nameEmer, relationshipEmer, homeTeleEmer, mobileEmer, workTeleEmer, nameDepen, salaryComponent, amountSalary , exemptionNumber;
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
		
		editStreetName01 = fakeData.getStreetAddress();
		editStreetName02 = fakeData.getStreetAddress();
		editCity = fakeData.getCityAddress();
		editState = fakeData.getStateAddress();
		editZipCode = fakeData.getPostalCode(); 
		editHomephone = "012355" + String.valueOf(RandomInt());
		editMobile = "092385" + String.valueOf(RandomInt());
		editWorkTelephone ="034555" + String.valueOf(RandomInt()); 
		editWorkEmail = fakeData.getEmailAddress();
		editOtherEmail = fakeData.getEmailAddress();
		
		nameEmer = fakeData.getFirstName();
		relationshipEmer = fakeData.getRelationship();
		homeTeleEmer = "01355" + String.valueOf(RandomInt());
		mobileEmer = "09555" + String.valueOf(RandomInt());
		workTeleEmer = "03385" + String.valueOf(RandomInt());
		
		nameDepen = fakeData.getFirstName();
		
		salaryComponent ="CTO";
		amountSalary = "4" + String.valueOf(RandomInt());
		
		exemptionNumber = "12";
		
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
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 05: Get Employee ID: " + lastName);
		employeeID = addEmpPage.getValueTextBoxByID(driver, "employeeId", "value");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 06: Click to 'Create Login Details' Checkbox");
		addEmpPage.clickOnButtonByID(driver, "chkLogin");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 07: Input to User Name: " + empUserName);
		addEmpPage.inputTextBoxByID(driver, empUserName, "user_name");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 08: Input to Password: " + empPassword);
		addEmpPage.inputTextBoxByID(driver, empPassword, "user_password");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 09: Input to Confirm Password: " + empPassword);
		addEmpPage.inputTextBoxByID(driver, empPassword, "re_password");
	
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 10: Select status is 'Enable'");
		addEmpPage.selectDropdownByIDAndText(driver, "status", "Enabled");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 11: Click to 'Save' Button");
		addEmpPage.clickOnButtonByID(driver, "btnSave");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 12: Click to SubMenu: 'Employee List'");
		addEmpPage.clickOnMenuAndSubMenuByName(driver,"PIM","Employee List");
		empListPage = PageGeneratorManager.getEmpListPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 13: Input to Employee Name: "+  firstName);
		empListPage.isJQueryAjaxLoadedSuccess(driver);
		empListPage.inputTextBoxByID(driver, fullName, "empsearch_employee_name_empName");
		empListPage.isJQueryAjaxLoadedSuccess(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 14: Click to 'Search' Button");
		empListPage.clickOnButtonByID(driver, "searchBtn");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 15: Verify First Name in Table: " + firstName);
		verifyEquals(empListPage.getValueByTableIdAndColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), firstName);
	
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 16: Verify Last Name in Table: "+ lastName);
		verifyEquals(empListPage.getValueByTableIdAndColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), lastName);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 17: Verify Employee ID in Table: "+ employeeID);
		verifyEquals(empListPage.getValueByTableIdAndColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "01_Add_New_Employee - Step 18: Logout from the system");
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
	
	@Test
	public void TC_04_Contact_Details(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_04_Contact_Details");
		ExtentTestManager.getTest().log(LogStatus.INFO, "04_Contact_Info - Step 01: Open Contact Details menu at My info Menu Header");
		empInfoPage.openInfoMenuByName("Contact Details");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "04_Contact_Info - Step 02: Verify all fields is disabled");
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "contact_street1"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "contact_street2"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "contact_city"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "contact_province"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "contact_emp_zipcode"));
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "contact_country"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "contact_emp_hm_telephone"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "contact_emp_mobile"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "contact_emp_work_telephone"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "contact_emp_work_email"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "contact_emp_oth_email"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "04_Contact_Info - Step 03: Click on Edit button");
		empInfoPage.clickOnButtonByID(driver, "btnSave");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 04: Input Street Name 01 : " + editStreetName01);
		empInfoPage.inputTextBoxByID(driver, editStreetName01, "contact_street1");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 05: Input Street Name 02: " + editStreetName02);
		empInfoPage.inputTextBoxByID(driver, editStreetName02, "contact_street2");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 06: Input city Name: " + editCity);
		empInfoPage.inputTextBoxByID(driver, editCity, "contact_city");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 07: Input State/Province: " + editState);
		empInfoPage.inputTextBoxByID(driver, editState, "contact_province");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 08: Input Zip/Postal Code: " + editZipCode);
		empInfoPage.inputTextBoxByID(driver, editZipCode, "contact_emp_zipcode");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 09: Select contry is: American Samoa");
		empInfoPage.selectDropdownByIDAndText(driver, "contact_country", "American Samoa");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 10: Input Home Telephone: " + editHomephone);
		empInfoPage.inputTextBoxByID(driver, editHomephone, "contact_emp_hm_telephone");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 11: Input Mobile: " + editMobile);
		empInfoPage.inputTextBoxByID(driver, editMobile, "contact_emp_mobile");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 12: Input Work Telephone: " + editWorkTelephone);
		empInfoPage.inputTextBoxByID(driver, editWorkTelephone, "contact_emp_work_telephone");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 13: Input Work Email: " + editWorkEmail);
		empInfoPage.inputTextBoxByID(driver, editWorkEmail, "contact_emp_work_email");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 14: Input SOther Email: " + editOtherEmail);
		empInfoPage.inputTextBoxByID(driver, editOtherEmail, "contact_emp_oth_email");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "04_Contact_Info - Step 15: Click on Save button");
		empInfoPage.clickOnButtonByID(driver, "btnSave");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "04_Contact_Info - Step 16: Verify success message upload is displayed");
		verifyTrue(empInfoPage.isSuccessMessageDisplayed(driver,"Successfully Saved"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 17: Verify success new Address Street 1 is displayed: "+ editStreetName01);
		verifyEquals(empInfoPage.getValueTextBoxByID(driver,"contact_street1","value"), editStreetName01);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 18: Verify success new Address Street 2 is displayed: "+ editStreetName02);
		verifyEquals(empInfoPage.getValueTextBoxByID(driver,"contact_street2","value"), editStreetName02);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 19: Verify success new city is displayed: "+ editCity);
		verifyEquals(empInfoPage.getValueTextBoxByID(driver,"contact_city","value"), editCity);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 20: Verify success new State/Province is displayed: "+ editState);
		verifyEquals(empInfoPage.getValueTextBoxByID(driver,"contact_province","value"), editState);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 21: Verify success new Zip/Postal Code is displayed: "+ editZipCode);
		verifyEquals(empInfoPage.getValueTextBoxByID(driver,"contact_emp_zipcode","value"), editZipCode);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 22: Verify success new Country is displayed: American Samoa");
		verifyEquals(empInfoPage.getValueSelectedDropdownByID(driver,"contact_country"), "American Samoa");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 23: Verify success new Home Telephone is displayed: "+ editHomephone);
		verifyEquals(empInfoPage.getValueTextBoxByID(driver,"contact_emp_hm_telephone","value"), editHomephone);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 24: Verify success new Mobile is displayed: "+ editMobile);
		verifyEquals(empInfoPage.getValueTextBoxByID(driver,"contact_emp_mobile","value"), editMobile);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 25: Verify success new Work Telephone is displayed: "+ editWorkTelephone);
		verifyEquals(empInfoPage.getValueTextBoxByID(driver,"contact_emp_work_telephone","value"), editWorkTelephone);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 26: Verify success new Work Email is displayed: "+ editWorkEmail);
		verifyEquals(empInfoPage.getValueTextBoxByID(driver,"contact_emp_work_email","value"), editWorkEmail);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "03_Edit_Info - Step 27: Verify success new Other Email is displayed: "+ editOtherEmail);
		verifyEquals(empInfoPage.getValueTextBoxByID(driver,"contact_emp_oth_email","value"), editOtherEmail);
		
		ExtentTestManager.endTest();
	}
	
	@Test
	public void TC_05_Emergency_Contacts(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_05_Emergency_Contacts");
		ExtentTestManager.getTest().log(LogStatus.INFO, "04_Contact_Info - Step 01: Open Contact Details menu at My info Menu Header");
		empInfoPage.openInfoMenuByName("Emergency Contacts");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "05_Emergency_Contacts - Step 02: Click on Add button");
		empInfoPage.clickOnButtonByID(driver, "btnAddContact");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "05_Emergency_Contacts - Step 03: Input Name: " + nameEmer );
		empInfoPage.inputTextBoxByID(driver, nameEmer, "emgcontacts_name");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "05_Emergency_Contacts - Step 04: Input Relationship: " +relationshipEmer);
		empInfoPage.inputTextBoxByID(driver, relationshipEmer, "emgcontacts_relationship");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "05_Emergency_Contacts - Step 05: Input Home Telephone: " +homeTeleEmer);
		empInfoPage.inputTextBoxByID(driver, homeTeleEmer, "emgcontacts_homePhone");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "05_Emergency_Contacts - Step 06: Input Mobile: "+ mobileEmer);
		empInfoPage.inputTextBoxByID(driver, mobileEmer, "emgcontacts_mobilePhone");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "05_Emergency_Contacts - Step 07: Input Work Telephone: "+ workTeleEmer);
		empInfoPage.inputTextBoxByID(driver, workTeleEmer, "emgcontacts_workPhone");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "05_Emergency_Contacts - Step 08: Click on Save button");
		empInfoPage.clickOnButtonByID(driver, "btnSaveEContact");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "05_Emergency_Contacts - Step 09: Verify Name in Table: "+ nameEmer);
		verifyEquals(empInfoPage.getValueByTableIdAndColumnNameAndRowIndex(driver, "emgcontact_list", "Name", "1"), nameEmer);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "05_Emergency_Contacts - Step 10: Verify Name in Table: "+ relationshipEmer);
		verifyEquals(empInfoPage.getValueByTableIdAndColumnNameAndRowIndex(driver, "emgcontact_list", "Relationship", "1"), relationshipEmer);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "05_Emergency_Contacts - Step 11: Verify Name in Table: "+ homeTeleEmer);
		verifyEquals(empInfoPage.getValueByTableIdAndColumnNameAndRowIndex(driver, "emgcontact_list", "Home Telephone", "1"), homeTeleEmer);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "05_Emergency_Contacts - Step 12: Verify Name in Table: "+ mobileEmer);
		verifyEquals(empInfoPage.getValueByTableIdAndColumnNameAndRowIndex(driver, "emgcontact_list", "Mobile", "1"), mobileEmer);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "05_Emergency_Contacts - Step 13: Verify Name in Table: "+ workTeleEmer);
		verifyEquals(empInfoPage.getValueByTableIdAndColumnNameAndRowIndex(driver, "emgcontact_list", "Work Telephone", "1"), workTeleEmer);
		
		
		ExtentTestManager.endTest();
	}
	

	@Test
	public void TC_06_Assigned_Dependents(Method method) {	
		ExtentTestManager.startTest(method.getName(), "TC_06_Assigned_Dependents");
		ExtentTestManager.getTest().log(LogStatus.INFO, "06_Assigned_Dependents - Step 01: Open Dependent menu at My info Menu");
		empInfoPage.openInfoMenuByName("Dependents");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "06_Assigned_Dependents - Step 02: Click on Add button");
		empInfoPage.clickOnButtonByID(driver, "btnAddDependent");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "06_Assigned_Dependents - Step 03: Input Name: " + nameDepen );
		empInfoPage.inputTextBoxByID(driver, nameDepen, "dependent_name");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "06_Assigned_Dependents - Step 04: Select Relationship: Child");
		empInfoPage.selectDropdownByIDAndText(driver, "dependent_relationshipType", "Child");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "06_Assigned_Dependents - Step 05: Click on Save button");
		empInfoPage.clickOnButtonByID(driver, "btnSaveDependent");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "06_Assigned_Dependents - Step 06: Verify success message upload is displayed");
		verifyTrue(empInfoPage.isSuccessMessageDisplayed(driver,"Successfully Saved"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "06_Assigned_Dependents - Step 13: Verify Name in Table: "+ nameDepen);
		verifyEquals(empInfoPage.getValueByTableIdAndColumnNameAndRowIndex(driver, "dependent_list", "Name", "1"), nameDepen);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "06_Assigned_Dependents - Step 13: Verify Relationship in Table: Child");
		verifyEquals(empInfoPage.getValueByTableIdAndColumnNameAndRowIndex(driver, "dependent_list", "Relationship", "1"), "child");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "06_Assigned_Dependents - Step 14: Logout from the system");
		loginPage = empInfoPage.logoutFromTheSystem(driver, "Logout");
		
		ExtentTestManager.endTest();
	}
	
	@Test
	public void TC_07_Edit_And_View_Job(Method method) {	
		ExtentTestManager.startTest(method.getName(), "TC_07_Edit_And_View_Job");
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 01: Login in to the system with Admin role");
		dashBoardPage = loginPage.loginToTheSystem(driver, "txtUsername","txtPassword","btnLogin",adminUsername,adminPassword);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 02: Open Menu Header: 'PIM' and Sub Menu: 'Employee List'");
		dashBoardPage.clickOnMenuAndSubMenuByName(driver,"PIM","Employee List");
		empListPage = PageGeneratorManager.getEmpListPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 03: Input to Employee Name: "+  firstName);
		empListPage.isJQueryAjaxLoadedSuccess(driver);
		empListPage.inputTextBoxByID(driver, editFullName, "empsearch_employee_name_empName");
		empListPage.isJQueryAjaxLoadedSuccess(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 04: Click to 'Search' Button");
		empListPage.clickOnButtonByID(driver, "searchBtn");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 05: Click to Employee's name in table");
		empListPage.clickValueByTableIdAndColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1");
		empInfoPage = PageGeneratorManager.getEmpInfoPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 06: Open Dependent menu at My info Menu");
		empInfoPage.openInfoMenuByName("Job");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 07: Verify all fields is disabled");
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "job_job_title"));
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "job_emp_status"));
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "job_eeo_category"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "job_joined_date"));
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "job_sub_unit"));
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "job_location"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "job_contract_start_date"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "job_contract_end_date"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 08: Click on Edit button");
		empInfoPage.clickOnButtonByID(driver, "btnSave");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 09: Select Job Title: Account Assistant");
		empInfoPage.selectDropdownByIDAndText(driver, "job_job_title", "Account Assistant");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 10: Select Employment Status: Freelance");
		empInfoPage.selectDropdownByIDAndText(driver, "job_emp_status", "Freelance");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 11: Select Job Category: Office and Clerical Workers");
		empInfoPage.selectDropdownByIDAndText(driver, "job_eeo_category", "Office and Clerical Workers");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 12: Select Sub Unit: Engineering");
		empInfoPage.selectDropdownByIDAndText(driver, "job_sub_unit", "Engineering");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 13: Select Location: Canadian Regional HQ");
		empInfoPage.selectDropdownByIDAndText(driver, "job_location", "Canadian Regional HQ");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 14: Click on Save button");
		empInfoPage.clickOnButtonByID(driver, "btnSave");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 15: Verify success message upload is displayed");
		verifyTrue(empInfoPage.isSuccessMessageDisplayed(driver,"Successfully Updated"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 14: Logout from the system");
		loginPage = empInfoPage.logoutFromTheSystem(driver, "Logout");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 15: Login with user name: "+empUserName +" and password "+empPassword);
		dashBoardPage = loginPage.loginToTheSystem(driver, "txtUsername","txtPassword","btnLogin",empUserName,empPassword);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 16: Open Menu Header: 'My Info'");
		dashBoardPage.clickOnMenuByName(driver, "My Info");
		empInfoPage = PageGeneratorManager.getEmpInfoPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 17: Open Job menu at My info Menu");
		empInfoPage.openInfoMenuByName("Job");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 18: Verify Job Title selected is Account Assistant");
		verifyEquals(empInfoPage.getValueSelectedDropdownByID(driver,"job_job_title"), "Account Assistant");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 19: Verify Employment Status selected is Freelance");
		verifyEquals(empInfoPage.getValueSelectedDropdownByID(driver,"job_emp_status"), "Freelance");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 20: Verify Job Category selected is Office and Clerical Workers");
		verifyEquals(empInfoPage.getValueSelectedDropdownByID(driver,"job_eeo_category"), "Office and Clerical Workers");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 21: Verify Sub Unit selected is Engineering");
		verifyEquals(empInfoPage.getValueSelectedDropdownByID(driver,"job_sub_unit"), "Engineering");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 22: Verify Location selected is Canadian Regional HQ");
		verifyEquals(empInfoPage.getValueSelectedDropdownByID(driver,"job_location"), "Canadian Regional HQ");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 23: Verify all fields is disabled");
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "job_job_title"));
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "job_emp_status"));
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "job_eeo_category"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "job_joined_date"));
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "job_sub_unit"));
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "job_location"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "job_contract_start_date"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "job_contract_end_date"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "07_Edit_And_View_Job - Step 24: Logout from the system");
		loginPage = empInfoPage.logoutFromTheSystem(driver, "Logout");
		
		ExtentTestManager.endTest();
	}
	
	@Test
	public void TC_08_Edit_And_View_Salary(Method method) {	
		ExtentTestManager.startTest(method.getName(), "TC_08_Edit_And_View_Salary");
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 01: Login in to the system with Admin role");
		dashBoardPage = loginPage.loginToTheSystem(driver, "txtUsername","txtPassword","btnLogin",adminUsername,adminPassword);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 02: Open Menu Header: 'PIM' and Sub Menu: 'Employee List'");
		dashBoardPage.clickOnMenuAndSubMenuByName(driver,"PIM","Employee List");
		empListPage = PageGeneratorManager.getEmpListPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 03: Input to Employee Name: "+  firstName);
		empListPage.isJQueryAjaxLoadedSuccess(driver);
		empListPage.inputTextBoxByID(driver, editFullName, "empsearch_employee_name_empName");
		empListPage.isJQueryAjaxLoadedSuccess(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 04: Click to 'Search' Button");
		empListPage.clickOnButtonByID(driver, "searchBtn");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 05: Click to Employee's name in table");
		empListPage.clickValueByTableIdAndColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1");
		empInfoPage = PageGeneratorManager.getEmpInfoPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 06: Open Dependent menu at My info Menu");
		empInfoPage.openInfoMenuByName("Salary");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 07: Click on Add button");
		empInfoPage.clickOnButtonByID(driver, "addSalary");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 08: Select Pay Grade is: Grade 2");
		empInfoPage.selectDropdownByIDAndText(driver, "salary_sal_grd_code", "Grade 2");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 09: Input Salary Component is: "+salaryComponent);
		empInfoPage.inputTextBoxByID(driver, salaryComponent, "salary_salary_component");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 10: Select Pay Frequency is: Monthly");
		empInfoPage.selectDropdownByIDAndText(driver, "salary_payperiod_code", "Monthly");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 11: Select Currency is: United States Dollar");
		empInfoPage.selectDropdownByIDAndText(driver, "salary_currency_id", "United States Dollar");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 12: Input amount is: " + amountSalary);
		empInfoPage.inputTextBoxByID(driver, amountSalary, "salary_basic_salary");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 13: Click on Save button");
		empInfoPage.clickOnButtonByID(driver, "btnSalarySave");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 15: Verify success message upload is displayed");
		verifyTrue(empInfoPage.isSuccessMessageDisplayed(driver,"Successfully Saved"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 14: Logout from the system");
		loginPage = empInfoPage.logoutFromTheSystem(driver, "Logout");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 15: Login with user name: "+empUserName +" and password "+empPassword);
		dashBoardPage = loginPage.loginToTheSystem(driver, "txtUsername","txtPassword","btnLogin",empUserName,empPassword);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 16: Open Menu Header: 'My Info'");
		dashBoardPage.clickOnMenuByName(driver, "My Info");
		empInfoPage = PageGeneratorManager.getEmpInfoPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 17: Open Job menu at My info Menu");
		empInfoPage.openInfoMenuByName("Salary");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 18: Verify Salary Component in Table: "+ salaryComponent);
		verifyEquals(empInfoPage.getValueByTableIdAndColumnNameAndRowIndexOfFirstColumn( "tblSalary", "Salary Component", "1"), salaryComponent);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 19: Verify Pay Frequency in Table: Monthly");
		verifyEquals(empInfoPage.getValueByTableIdAndColumnNameAndRowIndexNoHeader(driver, "tblSalary", "Pay Frequency", "1"), "Monthly");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 20: Verify Currency in Table: United States Dollar");
		verifyEquals(empInfoPage.getValueByTableIdAndColumnNameAndRowIndexNoHeader(driver, "tblSalary", "Currency", "1"), "United States Dollar");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 21: Verify Amount in Table: "+ amountSalary);
		verifyEquals(empInfoPage.getValueByTableIdAndColumnNameAndRowIndexNoHeader(driver, "tblSalary", "Amount", "1"), amountSalary);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "08_Edit_And_View_Salary - Step 22: Logout from the system");
		loginPage = empInfoPage.logoutFromTheSystem(driver, "Logout");
		ExtentTestManager.endTest();
	}
	
	@Test
	public void TC_09_Tax_Exmptions(Method method) {	
		ExtentTestManager.startTest(method.getName(), "TC_09_Tax_Exmptions");
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 01: Login in to the system with Admin role");
		dashBoardPage = loginPage.loginToTheSystem(driver, "txtUsername","txtPassword","btnLogin",adminUsername,adminPassword);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 02: Open Menu Header: 'PIM' and Sub Menu: 'Employee List'");
		dashBoardPage.clickOnMenuAndSubMenuByName(driver,"PIM","Employee List");
		empListPage = PageGeneratorManager.getEmpListPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 03: Input to Employee Name: "+  firstName);
		empListPage.isJQueryAjaxLoadedSuccess(driver);
		empListPage.inputTextBoxByID(driver, editFullName, "empsearch_employee_name_empName");
		empListPage.isJQueryAjaxLoadedSuccess(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 04: Click to 'Search' Button");
		empListPage.clickOnButtonByID(driver, "searchBtn");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 05: Click to Employee's name in table");
		empListPage.clickValueByTableIdAndColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1");
		empInfoPage = PageGeneratorManager.getEmpInfoPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 06: Open Dependent menu at My info Menu");
		empInfoPage.openInfoMenuByName("Tax Exemptions");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 07: Verify all fields is disabled");
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "tax_federalStatus"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "tax_federalExemptions"));
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "tax_state"));
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "tax_stateStatus"));
		verifyFalse(empInfoPage.isTextBoxByIDEnabled(driver, "tax_stateExemptions"));
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "tax_unempState"));
		verifyFalse(empInfoPage.isSelectDropdownByIDEnabled(driver, "tax_workState"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 08: Click on Add button");
		empInfoPage.clickOnButtonByID(driver, "btnSave");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 09: Select Marital Status: Single");
		empInfoPage.selectDropdownByIDAndText(driver, "tax_federalStatus", "Single");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 10: input Exemptions is: exemptionNumber");
		empInfoPage.inputTextBoxByID(driver, exemptionNumber,"tax_federalExemptions");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 11: Select State is: Alabama");
		empInfoPage.selectDropdownByIDAndText(driver, "tax_state", "Alabama");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 12: Select Marital Status: Married");
		empInfoPage.selectDropdownByIDAndText(driver, "tax_stateStatus","Married");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 13: input Exemptions is: exemptionNumber");
		empInfoPage.inputTextBoxByID(driver, exemptionNumber,"tax_stateExemptions");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 14: Select Unemployment State is : Alaska");
		empInfoPage.selectDropdownByIDAndText(driver, "tax_unempState", "Alaska");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 15: Select Work State is: American Samoa");
		empInfoPage.selectDropdownByIDAndText(driver, "tax_workState", "American Samoa");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 16: Click on Add button");
		empInfoPage.clickOnButtonByID(driver, "btnSave");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 17: Verify success message upload is displayed");
		verifyTrue(empInfoPage.isSuccessMessageDisplayed(driver,"Successfully Saved"));
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 14: Logout from the system");
		loginPage = empInfoPage.logoutFromTheSystem(driver, "Logout");
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 15: Login with user name: "+empUserName +" and password "+empPassword);
		dashBoardPage = loginPage.loginToTheSystem(driver, "txtUsername","txtPassword","btnLogin",empUserName,empPassword);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 16: Open Menu Header: 'My Info'");
		dashBoardPage.clickOnMenuByName(driver, "My Info");
		empInfoPage = PageGeneratorManager.getEmpInfoPage(driver);
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "09_Tax_Exmptions - Step 17: Open Job menu at My info Menu");
		empInfoPage.openInfoMenuByName("Tax Exemptions");
		
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
