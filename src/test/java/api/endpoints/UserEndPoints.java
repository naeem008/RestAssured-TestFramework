package api.endpoints;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class UserEndPoints {
	
		
	 public static Response createUser(User payload)
	{
		 Response response =given()
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		 .body(payload)
		.when()
		 .post(Routes.post_url);
		
		return response;
	}
	 
	 public static Response readUser(String username) {
		    Response response = given()
		        .pathParam("username", username)
		        .when()
		        .get(Routes.get_url);
		    return response;
		}
	
	 public static Response updateUser(String userName,User payload)
		{
			 Response response =given()
			 .contentType(ContentType.JSON)
			 .accept(ContentType.JSON)
			 .pathParam("username", userName)
			 .body(payload)
			.when()
			 .put(Routes.update_url);
			
			return response;
		}
		 
	 public static Response deleteUser(String username) {
		    Response response = given()
		        .pathParam("username", username)
		        .when()
		        .delete(Routes.delete_url);
		    return response;
		}
}
