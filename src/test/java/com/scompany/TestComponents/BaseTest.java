package com.scompany.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		String browserName= System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");
			
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
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//read json file to String
		String jsonContent= FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		//reading string to HashMap -Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
			
		});
		return data;
	}
	
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts= (TakesScreenshot)driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir")+"//reports"+testcaseName+".png");
		FileUtils.copyFile(Source, destination);
		return System.getProperty("user.dir")+"//reports"+testcaseName+".png";
	}
	
	@BeforeMethod (alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver= initializeDriver();
		landingPage= new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	 }
	
	@AfterMethod (alwaysRun = true)
	public void  closeApplication() {
		//driver.close();
		driver.quit();
	 }

}
