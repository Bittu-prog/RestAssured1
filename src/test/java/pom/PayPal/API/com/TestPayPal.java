package pom.PayPal.API.com;



import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.PayPal.API.POJO.Orders;
import com.PayPal.API.POJO.PurchaseUnits;
import com.generic.PayPalAPI.API.Baseclass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class TestPayPal {

	static String access_token;
	static String client_id="ASlQzQltkoRwKGrfIr03REC0GtBh5qY7kmM3RbaZi872t382e8zfswVzcvmIIFjojihwWOeCUjIE15DP";
	static String secret="EEuqcA2ngUHYAvDL2bl5Mh6X6Y4iOyOoAa3rKyGM7btqlO4IgVYM4QLOrFA3Pm865m0aXd-m4MVUOIqn";
	static String orderId;
	
	@Test(priority = 1)	
	public void getAuthKey()
	{
		
RestAssured.baseURI="https://api.sandbox.paypal.com/";
		
		
		Response response = given().param("grant_type", "client_credentials")
		.auth().preemptive()
		.basic(client_id, secret).post("/v1/oauth2/token");
		
		
		response.prettyPrint();
		
		access_token = response.jsonPath().get("access_token").toString();
		System.out.println(access_token);
	}
	
	@Test(priority = 2, dependsOnMethods = "getAuthKey" )
	public void CreateOrder() 
	{
	ArrayList<PurchaseUnits> list =new ArrayList<PurchaseUnits>(); 
	list.add(new PurchaseUnits("USD", "500.00"));
		Orders order=new Orders("CAPTURE", list);
//		String jsonBody="{\r\n"
//				+ "  \"intent\": \"CAPTURE\",\r\n"
//				+ "  \"purchase_units\": [\r\n"
//				+ "    {\r\n"
//				+ "      \"reference_id\": \"d9f80740-38f0-11e8-b467-0ed5f89f718b\",\r\n"
//				+ "      \"amount\": {\r\n"
//				+ "        \"currency_code\": \"USD\",\r\n"
//				+ "        \"value\": \"100.00\"\r\n"
//				+ "      }\r\n"
//				+ "    }\r\n"
//				+ "  ]\r\n"
//				+ "}";
	RestAssured.baseURI="https://api-m.sandbox.paypal.com";	
	
	
	Response response = given()
	.contentType(ContentType.JSON)
	.auth().oauth2(access_token)
	.body(order)
	.post("/v2/checkout/orders");
	
	response.prettyPrint();
	System.out.println(response.statusCode());
	Assert.assertEquals(response.jsonPath().get("status"), "CREATED");
	orderId=response.jsonPath().get("id").toString();
	}
	
	@Test(priority=3, dependsOnMethods = {"getAuthKey","CreateOrder"})
	public void getOrder()
	{
		RestAssured.baseURI="https://api-m.sandbox.paypal.com";	
		System.out.println("-----getting the order-------");
		
		
		Response response = given()
		.contentType(ContentType.JSON)
		.auth().oauth2(access_token)
		
		.get("/v1/checkout/orders"+"/"+orderId);
		
		Assert.assertEquals(response.getStatusCode(), 200); 
		
	}
}
