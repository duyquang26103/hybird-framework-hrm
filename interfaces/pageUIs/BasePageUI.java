package pageUIs;

public class BasePageUI {
	public static final String INPUT_TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String BUTTON_BY_ID = "//input[@id='%s']";
	public static final String MENU_BY_NAME = "//div[@id='mainMenu']//a[contains(.,'%s')]";
	public static final String SUB_MENU_BY_NAME = "//div[@id='mainMenu']//a[contains(.,'%s')]";
	public static final String SELECT_DROPDOWN_BY_ID = "//select[@id='%s']";
	public static final String TABLE_BY_ID_AND_ROW_HEADER_BY_NAME = "//table[@id='%s']//th[contains(.,'%s')]/preceding-sibling::th";
	public static final String TABLE_BY_ID_AND_ROW_BY_INDEX_COLUMN_BY_INDEX = "//table[@id='%s']/tbody/tr[%s]/td[%s]/a";
}
