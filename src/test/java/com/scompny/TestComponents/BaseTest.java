package com.scompny.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.scompany.pageobjects.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;



public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")
				+"\\src\\main\\java\\com\\scompany\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName= prop.getProperty("browser");
			
		if(browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("Firefox")) {
			//firefox
		}else if(browserName.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver","edge.exe");
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		driver= initializeDriver();
		landingPage= new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	 }
	
	@AfterMethod
	public void  closeApplication() {
		//driver.close();
		driver.quit();
	 }

}
