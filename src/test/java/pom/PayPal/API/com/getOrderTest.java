package pom.PayPal.API.com;

import org.testng.annotations.Test;

import com.generic.PayPalAPI.API.Baseclass;

import org.testng.Assert;
import org.testng.AssertJUnit;


import PayPal.OrderAPI;
import io.restassured.response.Response;



public class getOrderTest extends Baseclass
{
	
	@Test
public void getOrder()
{
		
		System.out.println("get order test start!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	String accessToken = OrderAPI.getAccessToken();
	Response response = OrderAPI.getOrder(accessToken);
	response.prettyPrint();
	Assert.assertEquals(response.getStatusCode(), 200);}
	
	
	
	
}
