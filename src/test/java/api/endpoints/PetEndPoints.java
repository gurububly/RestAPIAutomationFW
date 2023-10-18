package api.endpoints;

import api.payloads.petpayloads.PetPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetEndPoints {

    public static Response createPet(PetPayload petPayload) {

        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(petPayload)
                .when()
                .post(Routes.pet_postURL);
        return response;
    }

    public static Response createPetImage(String petid) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType("multipart/form-data")
                .pathParam("petId", petid)
                .multiPart("file", new File("C:\\RestApi_RestAssured_FW\\RESTAPI_E2E_Framework\\src\\test\\resources\\vivek.png"))
                //.body(petPayload)
                .when()
                .post(Routes.pet_postimageURL);
        return response;
    }

    public static Response getCreatedPet(String petid) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("petId", petid)
                .when()
                .get(Routes.pet_getURL);
        return response;
    }


    public static Response getPetByStatus(String status) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("status", status)
                .when().get(Routes.pet_statusURL);
        return response;
    }

    public static Response updatePet(PetPayload petPayload) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(petPayload)
                .when()
                .put(Routes.pet_updateURL);
        return response;
    }

    public static Response deletePet(String petid) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("petId",petid)
                .when()
                .delete(Routes.pet_deleteURL);
        return response;
    }
}
