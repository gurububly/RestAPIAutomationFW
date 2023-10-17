package api.testcases;

import api.endpoints.UserEndPoints;
import api.payload.UserPayload;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserTest {

    Faker faker;
    UserPayload userPayload;
    public Logger logger;

    @BeforeClass
    public void generateTestData() {
        faker = new Faker();
        userPayload = new UserPayload();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void verifyCreateUser() {
        logger.info("**********New user is created**************");
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 1)
    public void verifyGetUser() {
        logger.info("**********Verifying the newly created user**************");

        Response response = UserEndPoints.getUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void verifyUpdateUser() {
        logger.info("**********Updating the created user**************");

        userPayload.setFirstName(faker.name().firstName());
        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        logger.info("***************Read user data to check if the firstname is updated or not ***********");

        Response afterUpdateResponse = UserEndPoints.getUser(this.userPayload.getUsername());

        logger.info("**********After the update the response is *********");

        afterUpdateResponse.then().log().all();


    }

    @Test(priority = 3)
    public void verifyDeleteUser() {
        logger.info("**********verifying the delete user**************");

        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }
}
