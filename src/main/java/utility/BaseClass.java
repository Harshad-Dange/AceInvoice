package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	public static Properties properties;

	public BaseClass() {

		if(driver==null) {
			getBrowser();
		}
		
	}

	public String getPropertyFromFile(String property) {

		properties = new Properties();
		try {
			properties.load(new FileInputStream("src\\main\\java\\Configurations\\config.properties"));

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return properties.getProperty(property);
	}

	public void getBrowser() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

}
