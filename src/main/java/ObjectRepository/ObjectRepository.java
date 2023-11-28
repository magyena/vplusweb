package ObjectRepository;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ObjectRepository {

	// Homepage Footer
	public ObjectRepository(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// public static By OthersButton = By.xpath("(//img[@alt='others'])[1]");
	@FindBy(how = How.XPATH, using = "(//img[@alt='others'])[1]")
	public WebElement othersButton;
	@FindBy(how = How.XPATH,using=  "(//img[@alt='home'])[1]")
	public WebElement HomeButton;
	@FindBy(how = How.XPATH,using=  "(//img[@alt='sport tv'])[1]")
	public WebElement SportButton;
	
	@FindBy(how = How.XPATH, using = "(//div[contains(text(),'Sign In')])[1]")
	public WebElement SignInButton;
	
	
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div/div/div/div[2]/div/div[2]/form/div/div/div/input")
	public WebElement FieldPhoneNumber;
	@FindBy(how = How.XPATH, using = "(//button[normalize-space()='Continue with Email'])[1]")
	public WebElement ContinueWithEmail;
	@FindBy(how = How.XPATH, using = "(//button[normalize-space()='Continue'])[1]")
	public WebElement ContinueButton;
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div/div/div/div[2]/div/div[2]/form/div/div/div[1]/input")
	public WebElement FieldPassword;
	@FindBy(how = How.XPATH, using = "(//input[@placeholder='-'])[1]")
	public WebElement otp1;
	@FindBy(how = How.XPATH, using = "(//input[@placeholder='-'])[2]")
	public WebElement otp2;
	@FindBy(how = How.XPATH, using = "(//input[@placeholder='-'])[3]")
	public WebElement otp3;
	@FindBy(how = How.XPATH, using = "(//input[@placeholder='-'])[4]")
	public WebElement otp4;


	public void clickOther() {
		othersButton.click();
	}

	public void clickSignIn() {
		SignInButton.click();

	}

	public void SendKeysPhoneNumber(String Hasil) {
		FieldPhoneNumber.sendKeys(Hasil);
	}

	public void continueButton() {
		ContinueButton.click();
	}
	public void fieldpassword(Map<String, String> data) {
		FieldPassword.sendKeys(data.get("password"));
	}
	public void inputotp1(String Otp1) {
		otp1.sendKeys(String.valueOf(Otp1));
	}
	public void inputotp2(String Otp2) {
		otp2.sendKeys(String.valueOf(Otp2));
	}
	public void inputotp3(String Otp3) {
		otp3.sendKeys(String.valueOf(Otp3));
	}
	public void inputotp4(String Otp4) {
		otp4.sendKeys(String.valueOf(Otp4));
	}
//	}public void clickOther()
////	{
////		othersButton.click();
//	}public void clickOther()
//	{
//		othersButton.click();
//	}public void clickOther()
//	{
//		othersButton.click();
//	}

	public static By LiveTvButton = By.xpath("(//img[@alt='live tv'])[1]");
	public static By GamesButton = By.xpath("(//img[@alt='games'])[1]");
	public static By ContinueWithGoogle = By.xpath("(//button[normalize-space()='Continue with Google'])[1]");
	public static By ContinueWithFacebook = By.xpath("(//button[normalize-space()='Continue with Facebook'])[1]");
	//public static By ContinueButton = By.xpath("(//button[normalize-space()='Continue'])[1]");
	//public static By FieldPassword = By.xpath("(//input[@placeholder='Your password'])[1]");
	public static By FieldEmail = By.xpath("(//input[@placeholder='example@mail.com'])[1]");
//	public static By OTP1 = By.xpath("(//input[@placeholder='-'])[1]");
//	public static By OTP2 = By.xpath("(//input[@placeholder='-'])[2]");
//	public static By OTP3 = By.xpath("(//input[@placeholder='-'])[3]");
//	public static By OTP4 = By.xpath("(//input[@placeholder='-'])[4]");
////	

}
