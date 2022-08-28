package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.*;

import io.restassured.http.ContentType;


public class GetAndPostExamples {

	@Test
	public void tesGet() {
		
		baseURI = "https://reqres.in/api";
		
		given().
			get("/users?page=2")
		.then().
			statusCode(200)
			.body("data[4].first_name", equalTo("George"))
			.body("data.first_name", hasItems("George", "Rachel"));
	}
	
	
	@Test
	public void tesPost() {
		Map<String, Object> map = new HashMap<String, Object>();
		
//		map.put("name", "Sergio");
//		map.put("job", "Teacher");
//		System.out.println(map);
//		JSONObject request = new JSONObject(map);
//		System.out.println(request.toJSONString());
		
		JSONObject request = new JSONObject(map);
		
		request.put("name", "Sergio");
		request.put("job", "Teacher");
		
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/api";
		
		given()
			.header("Content-Type", "applitaction/json")
			.contentType(ContentType.JSON).accept(ContentType.JSON)
			.body(request.toJSONString()).
		when()
			.post("/users?page=2").
		then()
			.statusCode(201)
			.log()
			.all();
	}
	
}
