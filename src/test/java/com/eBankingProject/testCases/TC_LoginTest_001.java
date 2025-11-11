package com.eBankingProject.testCases;

import java.io.IOException;


import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.eBankingProject.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass
{
	@Test
	public void loginTest() throws IOException
	{
		//driver.get(baseURL);
		logger.info("URL is opened");
		LoginPage lp= new LoginPage(driver);
		//driver.manage().timeouts().implicitlyWait(10);
		lp.setUserName(username);
		logger.info("Entered Username");
		lp.setPassword(password);
		logger.info("Entered Password");
		lp.clickSubmit();
		//Thread.sleep(2000);
		System.out.println("Page title after login: " + driver.getTitle());
		
		if(driver.getTitle().equalsIgnoreCase("GTPL Bank Manager HomePage")){
			Assert.assertTrue(true);
			logger.info("Login test passed");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 500);");
			lp.clickLogout();
					
				}
		else {
				captureScreen(driver, "loginTest"); //calling screenshot method we test case failed and passing driver and class name as parameter
				Assert.assertTrue(false);
				logger.info("Login test failed");
		}
				
	}

}
