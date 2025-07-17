package api.test;

import org.testng.annotations.Test;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.Assert;
import api.utilities.DataProviderClass;


public class DataDrivenTest {

   
	// create user
 
    @Test(priority = 1, dataProvider = "AllUserData", dataProviderClass = DataProviderClass.class)
    public void testCreateUsers(String id, String username, String firstName, String lastName,
                                String email, String password, String phone) {

        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        

        System.out.println("Creating user: " + username);

        Response response = UserEndPoints.createUser(user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }


    // Delete all user after create
    @Test(priority = 2, dataProvider = "DeleteUserData", dataProviderClass = DataProviderClass.class)
    public void testDeleteUsers(String username) {

        Response response = UserEndPoints.deleteUser(username);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200, "❌ User deletion failed");
    }
}

