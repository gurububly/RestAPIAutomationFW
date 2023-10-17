package api.testcases;

import api.endpoints.UserEndPoints;
import api.payload.UserPayload;
import api.utilities.DataProviders;
import api.utilities.ExcelUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserDataDrivenTest {


    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void verifyUserTest(String id, String username, String firstname, String lastname, String email, String phone, String password) {
        UserPayload userPayload = new UserPayload();
        userPayload.setId(Integer.parseInt(id));
        userPayload.setFirstName(firstname);
        userPayload.setLastName(lastname);
        userPayload.setPhone(phone);
        userPayload.setPassword(password);
        userPayload.setEmail(email);
        userPayload.setUsername(username);

        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 2, dataProvider = "userdata", dataProviderClass = DataProviders.class)
    public void verifyDeleteUserByName(String username) {

        Response response = UserEndPoints.deleteUser(username);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
