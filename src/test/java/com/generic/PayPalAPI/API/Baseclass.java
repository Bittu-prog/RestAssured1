package com.generic.PayPalAPI.API;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import pom.PayPal.Utilities.com.TestProperties;

public class Baseclass extends Constants
{
	public TestProperties property=new TestProperties(IConstant.propertypath);

	@BeforeSuite
	public void setUp() {
		
		client_Id=property.getDataFormatProperty("paypalClientID");
		baseuri=property.getDataFormatProperty("baseURI");
		secretKey=property.getDataFormatProperty("paypalSecret");
		RestAssured.baseURI=baseuri;
	}
	
	@AfterSuite
	public void tearDown() {
		
		
	}

	
}
