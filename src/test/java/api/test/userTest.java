package api.test;

import javax.sound.midi.VoiceStatus;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import junit.framework.Assert;

public class userTest {

	Faker faker;
	User userPayload;

	@BeforeClass
	public void setUpData() {

		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

	}

	@Test(priority = 1)
	public void testPostUser() {
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void testByUserName() {
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		response.then().log().all();
	}

	@Test(priority = 3)
	public void testUpdateByUserName() {

		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);

		Response responseAfterYpdate = UserEndPoints.readUser(this.userPayload.getUsername());

		Assert.assertEquals(responseAfterYpdate.getStatusCode(), 200);
	}

	 @Test(priority = 4)
	public void testDeleteByUserName() {
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
