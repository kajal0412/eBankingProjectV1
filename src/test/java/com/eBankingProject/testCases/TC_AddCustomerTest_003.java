package com.eBankingProject.testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.eBankingProject.pageObjects.AddNewCustomerPage;
import com.eBankingProject.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass{
	@Test
	public void AddCustomer() throws Exception{
		logger.info("URL is opened");
		LoginPage lp= new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		lp.setUserName(username);
		logger.info("Entered Username");
		lp.setPassword(password);
		logger.info("Entered Password");
		lp.clickSubmit();
		Thread.sleep(2000);
		
		AddNewCustomerPage cp=new AddNewCustomerPage(driver);
			cp.NewCuslkn();
			logger.info("providing customer details");
			cp.CustomerName("aashi");
			cp.Gender("male");
			cp.DathofBirth("12","04","1997");
			cp.Address("Borgaon");
			cp.City("Nagpur");
			cp.State("Maharashtra");
			cp.PinCode("440014");
			cp.PhoneNum("9145678456");	
			String email=randomstring()+"@gmail.com";
			cp.EmailId(email);
			cp.SetPass("dfghgbn");
			cp.Submit();
			
			Thread.sleep(3000);
			boolean result= driver.getPageSource().contains("Customer Registered Successfully!!!");
			if(result==true) {
				Assert.assertTrue(true);
				logger.info("test case passed");
			}
			else {
				logger.info("test case failed");
				captureScreen(driver,"AddCustomer");
				Assert.assertTrue(false);
			}
			
			
	}
	
	public String randomstring() {
		String generatedString=RandomStringUtils.randomAlphabetic(8);
		return (generatedString);
		
	}
}
	
	

