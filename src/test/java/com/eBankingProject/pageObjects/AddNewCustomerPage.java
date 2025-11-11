package com.eBankingProject.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewCustomerPage {
	WebDriver ldriver;
	public AddNewCustomerPage(WebDriver rdriver) {
		ldriver= rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//a[text()='New Customer']") WebElement NewCustomerlkn;
	@FindBy(name="name") WebElement CusName;
	@FindBy(name="rad1") WebElement Gender;
	@FindBy(name="dob") WebElement DOB;
	@FindBy(name="addr") WebElement Adr;
	@FindBy(name="city") WebElement City;
	@FindBy(name="state") WebElement State;
	@FindBy(name="pinno") WebElement Pin;
	@FindBy(name="telephoneno") WebElement Phone;
	@FindBy(name="emailid") WebElement Email;
	@FindBy(name="password") WebElement Pass;
	@FindBy(name="sub") WebElement Submit;
	
	public void NewCuslkn() {
		NewCustomerlkn.click();
	}
	public void CustomerName(String name) {
		CusName.sendKeys(name);
	}
	public void Gender(String rgender ) {
		Gender.click();
	}
	public void DathofBirth(String mm,String dd, String yy ) {
		DOB.sendKeys(mm);
		DOB.sendKeys(dd);
		DOB.sendKeys(yy);
	}
	public void Address(String adr) {
		Adr.sendKeys(adr);
	}
	public void PinCode(String pin ) {
		Pin.sendKeys(String.valueOf(pin));
	}
	public void PhoneNum(String phone ) {
		Phone.sendKeys(phone);
	}
	
	public void City(String city) {
		City.sendKeys(city);
	}
	public void State(String state ) {
		State.sendKeys(state);
	}
	public void EmailId(String email ) {
		Email.sendKeys(email);
	}
	public void SetPass(String pwd ) {
		Pass.sendKeys(pwd);
	}
	public void Submit() {
		Submit.click();
	}
	
	
	
	
	

}
