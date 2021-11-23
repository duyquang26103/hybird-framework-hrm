package pageUIs;

public class EmpInfoPageUI {
	public static final String MENU_BY_NAME = "//ul[@id='sidenav']//li/a[text()='%s']";
	public static final String IMAGE_LINK = "//img[@id='empPic']";
	
	public static final String CURENTCY_DROPDOWN = "//select[@id='salary_currency_id']";
	public static final String USD_OPTION_DROPDOWN = "//select[@id='salary_currency_id']//option[@value='USD']";
	
	public static final String TABLE_BY_ID_AND_ROW_HEADER_BY_NAME = "//table[@id='%s']//th[contains(.,'%s')]";
	public static final String TABLE_BY_ID_AND_ROW_BY_INDEX_COLUMN_BY_INDEX = "//table[@id='%s']/tbody/tr[%s]/td[%s]";
	
	public static final String PRE_TABLE_BY_ID_AND_ROW_HEADER_BY_NAME = "//p[@id='%s']/following-sibling::table//th[contains(.,'%s')]/preceding-sibling::th";
	public static final String PRE_TABLE_BY_ID_AND_ROW_BY_INDEX_COLUMN_BY_INDEX = "//p[@id='%s']/following-sibling::table/tbody/tr[%s]//td[%s]";
	
	
}
