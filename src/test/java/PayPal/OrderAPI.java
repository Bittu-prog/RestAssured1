package PayPal;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import com.PayPal.API.POJO.Orders;
import com.PayPal.API.POJO.PurchaseUnits;
import com.generic.PayPalAPI.API.Baseclass;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OrderAPI extends Baseclass {

	
	public static String getAccessToken() {


		Response response = given().param("grant_type", "client_credentials")
				.auth().preemptive()
				.basic(client_Id, secretKey).post("/v1/oauth2/token");
				
		
		String accesstoken = response.jsonPath().get("access_token").toString();
		return accesstoken;

	}
	
	
	

	public static Response createOrder(String access_token) {
		
		ArrayList<PurchaseUnits> list = new ArrayList<PurchaseUnits>();
		list.add(new PurchaseUnits("USD","500.00"));
		Orders order = new Orders("CAPTURE",list);
		
		Response resposne = given()
		.contentType(ContentType.JSON)
		.auth().oauth2(access_token)
		.body(order)
		.post("/v2/checkout/orders");
		
		orderId = resposne.jsonPath().get("id").toString();
		
		
		return resposne;
	}
	
	

	public static Response getOrder(String access_token) {

		System.out.println("Order id is : "+orderId);
		Response resposne = given()
				.contentType(ContentType.JSON)
				.auth().oauth2(access_token)
				
				.get("/v2/checkout/orders"+"/"+orderId);
		
		return resposne;
	}
}
