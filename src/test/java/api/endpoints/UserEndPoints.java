package api.endpoints;

import api.payloads.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints {


    public static Response createUser(UserPayload userPayload) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(userPayload)
                .when().post(Routes.user_postURL);
        return response;
    }


    public static Response getUser(String userName) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username", userName)
                .when()
                .get(Routes.user_getURL);
        return response;
    }

    public static Response updateUser(String userName, UserPayload userPayload) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username", userName)
                .body(userPayload)
                .when()
                .put(Routes.user_updateURL);
        return response;
    }

    public static Response deleteUser(String userName) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username", userName)
                .when()
                .delete(Routes.user_deleteURL);
        return response;
    }
}
