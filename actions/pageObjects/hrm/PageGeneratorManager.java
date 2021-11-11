package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	private static LoginPO loginPage;
	private static EmpListPO empListPage;
	private static EmpInfoPO empInfoPage;
	private static DashBoardPO dashBoradPage;
	private static AddEmpPO addEmpPage;

	private PageGeneratorManager() {

	}

	public static LoginPO getLoginPage(WebDriver driver) {
		return new LoginPO(driver);
	}

	public static EmpListPO getEmpListPage(WebDriver driver) {
		return new EmpListPO(driver);
	}

	public static EmpInfoPO getEmpInfoPage(WebDriver driver) {
		return new EmpInfoPO(driver);
	}

	public static DashBoardPO getDashBoardPage(WebDriver driver) {
		return new DashBoardPO(driver);
	}

	public static AddEmpPO getAddEmpPage(WebDriver driver) {
		return new AddEmpPO(driver);
	}

}
