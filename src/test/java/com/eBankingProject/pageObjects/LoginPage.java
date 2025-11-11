package com.eBankingProject.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;
	public LoginPage(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
		
	}
	@FindBy(name="uid") WebElement txtUserName;
	@FindBy(name="password") WebElement txtPassword;
	@FindBy(name="btnLogin") WebElement btnLogin;
	@FindBy(xpath="//a[text()='Log out']") WebElement clkLogout;
	
	public void setUserName(String uname) {
		txtUserName.sendKeys(uname);
		
	}
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
		
	}
	public void clickSubmit() {
		btnLogin.click();	
	}
	public void clickLogout() {
		
		    try {
		        // Scroll down to the logout link
		        JavascriptExecutor js = (JavascriptExecutor) ldriver;
		        js.executeScript("arguments[0].scrollIntoView(true);", clkLogout);
		        Thread.sleep(1000); // wait for 1 second

		        // Click on the logout link
		        js.executeScript("arguments[0].click();", clkLogout);
		    } 
		    catch (Exception e) {
		        System.out.println("Logout button not clickable: " + e.getMessage());
		    }
		}

	}

	
	


