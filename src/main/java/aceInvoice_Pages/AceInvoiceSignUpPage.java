package aceInvoice_Pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AceInvoiceSignUpPage {

	
	
	  WebDriver driver;
	  
	  public AceInvoiceSignUpPage(WebDriver driver) {
	  
	  this.driver = driver; 
	  PageFactory.initElements(driver, this);
	  
	  
	  }
	 @FindBy (xpath="//*[@role='alert']")
	 public WebElement flashMsg;

	public By Signin = By.xpath("//strong[contains(text(),'Sign Up')]");
	public By Email = By.xpath("//input[@name='email']");
	public By Get_Started = By.xpath("//input[@name='get_started']");
	public By Password = By.name("password");
	public By ConfirmPassword = By.name("password_confirmation");

	/* Basic Details */
	
	public By Firstname =  By.name("user[first_name]");
	public By Lastname =  By.name("user[last_name]");
	public By ContinueButton =  By.xpath("//input[@type='submit']");
	
	/* Organizathion Details */
	
	public By Orgname = By.xpath("//input[@name='name']");
	public By OrgEmail =  By.name("email");
	public By SkipLink = By.xpath("//a[@href='/onboarding/complete']");
	public By ContinueAppButton = By.xpath("//button[contains(text(), 'Continue to the app')]");
	
	
	
	public String generateRandomEmail() {
		
		return RandomStringUtils.randomAlphabetic(5).toLowerCase()+ "@yopmail.com";
	
	}
	
	public String generateName() {
		
		return RandomStringUtils.randomAlphabetic(5).toLowerCase();
	
	}
	public WebElement waitElementToBeClicable(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		return wait.until(ExpectedConditions.elementToBeClickable(element));

	}
}
