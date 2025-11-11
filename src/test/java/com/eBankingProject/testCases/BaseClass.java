package com.eBankingProject.testCases;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.eBankingProject.utilities.ReadConfig;
import com.eBankingProject.utilities.Reporting;

import org.apache.log4j.Logger;


public class BaseClass {
	//public String baseURL="https://demo.guru99.com/V1/index.php"; 
	//public String username="mngr643599";
	//public String password="AhygAde";
	// above string contain hardcoded value but in real project we don't use hard coded value instead we use properties file which contains baseurl, username, password, chromepath values
	ReadConfig readconfig= new ReadConfig();
	
	public String baseURL=readconfig.getApplicationURL(); 
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public static WebDriver driver;
	
	public static Logger logger;
	
	 
	@Parameters("browser") // use to launch test case in desired browser thats why we used parameters annotation
	@BeforeClass
	public void setup( String br) // String br to launch test case in desired browser
	{
		logger = Logger.getLogger("eBankingProject");
        PropertyConfigurator.configure("log4j.properties");
		//C:\\Users\\Admin\\eclipse-workspace\\eBankingProjectV1----this is home directory for driver instead of this we can use -----System.getProperty("user.dir")
		//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe"); hard coded
		
		//launching browser using config.properties file
		//System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		
		//Run test case on desired browser
		if(br.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		
		driver= new ChromeDriver();
		}
		else if(br.equals("msedge")) {
			System.setProperty("webdriver.chrome.driver",readconfig.getMsedgePath());
			
			driver= new EdgeDriver();
			}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // assign driver to reporting
        Reporting.driver = driver;

		driver.get(baseURL);
		
		
	}
	
	public static String captureScreen(WebDriver driver, String testName) throws IOException  {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + "/Screenshots/" + testName + "_" + dateName + ".png";
        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }
	
	@AfterClass
	
	public void tearDown() {
		driver.quit();
	}

}
