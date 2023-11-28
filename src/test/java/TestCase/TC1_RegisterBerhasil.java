package TestCase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jcraft.jsch.JSchException;


import Function.base;
import ObjectRepository.ObjectRepository;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC1_RegisterBerhasil extends base {

	@Test(dataProvider = "dataLogin")
	public void getPhone(HashMap<String, String> data) throws InterruptedException, JSchException, IOException {
		// Set up WebDriver using WebDriverManager
		driver.get("https://www.visionplus.id/");
		base generator = new base();
		String Hasilnya = generator.number();
		
		Thread.sleep(3000);

		ObjectRepository objectRepository = new ObjectRepository(driver);

		objectRepository.clickSignIn();
		Thread.sleep(1000);
		objectRepository.SendKeysPhoneNumber(Hasilnya);
		Thread.sleep(1000);
		objectRepository.continueButton();
		Thread.sleep(1000);
		objectRepository.fieldpassword(data);
		Thread.sleep(1000);
		objectRepository.continueButton();

		base extractor = new base();

		try {
			// Extract OTP
			String otpValuew = extractor.getOtp("otpget");

			char otp01 = otpValuew.charAt(0);
			char otp02 = otpValuew.charAt(1);
			char otp03 = otpValuew.charAt(2);
			char otp04 = otpValuew.charAt(3);
			
			objectRepository.inputotp1(String.valueOf(otp01));
			Thread.sleep(500);
			objectRepository.inputotp2(String.valueOf(otp02));
			Thread.sleep(500);
			objectRepository.inputotp3(String.valueOf(otp03));
			Thread.sleep(500);
			objectRepository.inputotp4(String.valueOf(otp04));
			Thread.sleep(500);

			Thread.sleep(10000); // You may want to handle InterruptedException appropriately
		} catch (JSchException | IOException e) {
			// Handle JSchException and IOException as needed
			e.printStackTrace();
		}
	}

	

	@DataProvider
	public Object[][] dataLogin() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "/src/test/java/DataJson.data/login.json");
		return new Object[][] { { data.get(0) } };

	}
}
