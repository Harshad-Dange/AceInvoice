package AceInvoice;

import org.testng.annotations.Test;

import aceInvoice_Pages.AceInvoiceSignUpPage;
import junit.framework.Assert;
import utility.BaseClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;

public class SignIn extends BaseClass {
	public AceInvoiceSignUpPage signPageObj;
	String FirstName;
	String LastName;
	String EmailID;

	@BeforeClass
	public void beforeClass() {

		signPageObj = new AceInvoiceSignUpPage(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void signIn() {

		driver.get("https://staging.aceinvoice.com/sign_in");

		driver.findElement(signPageObj.Signin).click();

		Assert.assertEquals("Create Account",driver.findElement(By.xpath("//h3[contains(text(), 'Create Account')]")).getText());

	}

	@Test(priority = 2)
	public void CreateAccount() {

		signPageObj.waitElementToBeClicable(driver.findElement(signPageObj.Email));

		EmailID = signPageObj.generateRandomEmail();

		driver.findElement(signPageObj.Email).sendKeys(EmailID);

		driver.findElement(signPageObj.Get_Started).click();
		
		System.out.println(EmailID);

		if (!driver.findElement(signPageObj.Password).isEnabled()) {

			driver.findElement(signPageObj.Email).clear();
			EmailID = signPageObj.generateRandomEmail();
			driver.findElement(signPageObj.Email).sendKeys(EmailID);
			driver.findElement(signPageObj.Get_Started).click();

		} else {

			driver.findElement(signPageObj.Password).sendKeys("123456789");

			driver.findElement(signPageObj.ConfirmPassword).sendKeys("123456789");

		}

		driver.findElement(signPageObj.ContinueButton).click();

		Assert.assertEquals("Basic details",driver.findElement(By.xpath("//h4[contains(text(), 'Basic details')]")).getText());

		FirstName = signPageObj.generateName();

		LastName = signPageObj.generateName();

		driver.findElement(signPageObj.Firstname).sendKeys(FirstName);

		driver.findElement(signPageObj.Lastname).sendKeys(LastName);

		driver.findElement(signPageObj.ContinueButton).click();

		driver.findElement(signPageObj.Orgname).sendKeys("Test Org");

		driver.findElement(signPageObj.OrgEmail).sendKeys(signPageObj.generateRandomEmail());

		signPageObj.waitElementToBeClicable(driver.findElement(signPageObj.ContinueButton));

		driver.findElement(signPageObj.ContinueButton).click();

		signPageObj.waitElementToBeClicable(signPageObj.flashMsg);

		Assert.assertEquals("Faild to validate newly created organization", "Organization was successfully created.",
				signPageObj.flashMsg.getText());
	}

	@Test(priority = 3)
	public void verifyAccount() {

		driver.findElement(signPageObj.SkipLink).click();

		signPageObj.waitElementToBeClicable(driver.findElement(signPageObj.ContinueAppButton));

		driver.findElement(signPageObj.ContinueAppButton).click();
		
		String name= driver.findElement(By.xpath("//tr[@role='row']/descendant::a")).getText();
		
		String email= driver.findElement(By.xpath("//tr[@role='row']/td[3]")).getText();
		
		System.out.println(name);
		
		Assert.assertEquals("---Failed to verify member name--",FirstName +" " + LastName, name);
		
		Assert.assertEquals("---Failed to verify email id--", EmailID , email);

		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
