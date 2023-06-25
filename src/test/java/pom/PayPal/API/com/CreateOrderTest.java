package pom.PayPal.API.com;

import org.testng.annotations.Test;

import com.generic.PayPalAPI.API.Baseclass;

import org.testng.AssertJUnit;


import PayPal.OrderAPI;
import io.restassured.response.Response;



public class CreateOrderTest extends Baseclass
{
	
	@Test
public void createOrder()
{
	String accessToken = OrderAPI.getAccessToken();
	Response response = OrderAPI.createOrder(accessToken);
	AssertJUnit.assertEquals(response.jsonPath().get("status"), "CREATED");
	
	System.out.println("Create Order Already Run");
}
	
	
}
