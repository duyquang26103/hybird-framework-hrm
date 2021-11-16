package utilities;

import com.github.javafaker.Faker;

public class DataUtil {
	private Faker faker;

	public DataUtil() {
		faker = new Faker();
	}

	public static DataUtil getData() {
		return new DataUtil();
	}

	public String getFirstName() {
		return faker.address().firstName();
	}

	public String getLastName() {
		return faker.address().lastName();
	}

	public String getUserName() {
		return faker.name().username();
	}

	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}
	
	public String getPassword() {
		return faker.internet().password();
	}

}
