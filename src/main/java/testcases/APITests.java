package testcases;

import org.testng.annotations.Test;

import com.sun.mail.iap.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

public class APITests {

	@Test(priority=1)
	public static void getAllPosts() {

		given()

				.when().get("https://jsonplaceholder.typicode.com/posts")

				.then().statusCode(200);
	}

	@Test(priority=2)
	public static void addNewPost() {

		HashMap data = new HashMap();
		data.put("userId", "11");
		data.put("id", "101");
		data.put("title", "consequatur ea labore ea harum");
		data.put("body", "quo deleniti praesentium dicta non quod\\naut est molestias\\nmolestias");

		Response res = (Response) given().contentType("application/json; charset=UTF-8").body(data).when()
				.post("https://jsonplaceholder.typicode.com/posts")

				.then().statusCode(200).log().body().extract().response();
		
	}
	
	@Test(priority=3)
	public static void getPost() {

		given()

				.when().get("https://jsonplaceholder.typicode.com/posts/101")

				.then()
					.statusCode(200)
					.log().body()
					.body("post.id", equalTo("101"))
					.body("post.title", equalTo("consequatur ea labore ea harum"));
	}
	
	@Test(priority=4)
	public static void updatePost() {
		
		HashMap data = new HashMap();
		data.put("userId", "11");
		data.put("id", "101");
		data.put("title", "top gadgets");
		data.put("body", "fitness hiking kayaking");

		given()
			.contentType("application/json; charset=UTF-8")
			.body(data)
		.when()
			.put("https://jsonplaceholder.typicode.com/posts/101")

		.then()
			.statusCode(200)
			.log().body()
			.body("post.id", equalTo("101"))
			.body("post.title", equalTo("top gadgets"))
			.body("post.body", equalTo("fitness hiking kayaking"));
	}
	
	@Test(priority=5)
	public static void deletePost() {
		
		Response res =
		(Response) given()
		.when()
			.delete("ttps://jsonplaceholder.typicode.com/posts/101")
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();			
		
		
	}

}
