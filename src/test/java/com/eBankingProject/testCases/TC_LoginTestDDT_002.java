package com.eBankingProject.testCases;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.eBankingProject.pageObjects.LoginPage;
import com.eBankingProject.utilities.XLUtilies;

public class TC_LoginTestDDT_002 extends BaseClass{
@Test(dataProvider="LoginData")
public void loginDDT(String user, String pwd) throws Exception
{
	LoginPage lp= new LoginPage(driver);
	lp.setUserName(user);
	//Logger logger2 = new Logger();
	logger.info("user name provided");
	lp.setPassword(pwd);
	logger.info("password provided");
	lp.clickSubmit();
	Thread.sleep(3000);;
	
	 if(isAlertPresent()==true) {
		 driver.switchTo().alert().accept(); // close alert
		 driver.switchTo().defaultContent();
		 Assert.assertTrue(false);
		 logger.warn("login failed");
	 }
	 
	 else {
		 Assert.assertTrue(true); 
		 logger.info("login passed");
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0, 500);");
		 
		 lp.clickLogout();
		 Thread.sleep(3000);
		 driver.switchTo().alert().accept();
		 driver.switchTo().defaultContent();
		 
		 
	 }
	 driver.get(baseURL);
}

public boolean isAlertPresent() { //user define method is created to check alert is present or not 
	try {
	driver.switchTo().alert();
	return true;
	}
	catch(NoAlertPresentException e) {
		return false;
		
	}
	
}

@DataProvider(name="LoginData") 
String[][] getData() throws IOException{
	String path= System.getProperty("user.dir")+"/src/test/java/com/eBankingProject/testData/LoginTestData.xlsx";
	int rownum=XLUtilies.getRowCount(path, "Sheet1");
	int colcount=XLUtilies.getCellCount(path, "Sheet1", 1);
	String logindata[][]=new String[rownum][colcount];
	for(int i=1;i<=rownum;i++) {
		for(int j=0;j<colcount;j++) {
			logindata[i-1][j]=XLUtilies.getCellData(path, "Sheet1",i, j);
		}
	}
	return logindata;
	
	
}

}
