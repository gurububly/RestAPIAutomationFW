package api.testcases;

import api.endpoints.PetEndPoints;
import api.endpoints.Routes;
import api.payloads.petpayloads.Category;
import api.payloads.petpayloads.PetPayload;
import api.payloads.petpayloads.Tags;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.plexus.logging.LoggerManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PetTest {
    Faker faker;
    Category category;
    Tags tags;
    PetPayload petPayLoad;
    public Logger logger;

    @BeforeClass
    public void generateTestData() throws JsonProcessingException {
        faker = new Faker();
        petPayLoad = new PetPayload();
        category = new Category();
        tags = new Tags();
        petPayLoad.setId(faker.idNumber().hashCode());
        category.setId(faker.idNumber().hashCode());
        category.setName(faker.name().firstName());
        petPayLoad.setCategory(category);
        petPayLoad.setName(faker.name().firstName());
        List<String> photoList = new ArrayList<>();
        photoList.add(faker.name().firstName());
        petPayLoad.setPhotoUrls(photoList);
        tags.setId(faker.idNumber().hashCode());
        tags.setName(faker.name().firstName());
        List<Tags> tagsList = new ArrayList<>();
        tagsList.add(tags);
        petPayLoad.setTags(tagsList);
        petPayLoad.setStatus(String.valueOf(faker.funnyName()));

//        ObjectMapper objectMapper = new ObjectMapper();
//        String val = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(petPayLoad);
//        System.out.println(val);
        logger = LogManager.getLogger(this.getClass());

    }

    @Test(priority = 1)
    public void verifyCreatePetTest() {
        logger.info("********* Created new pet ***********");
        Response response = PetEndPoints.createPet(petPayLoad);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("Pet id is :: " + petPayLoad.getId());
    }

    @Test(priority = 2)
    public void verifyGetPetTest() {
        logger.info("********* Getting the newly created pet ***********");
        Response response = PetEndPoints.getCreatedPet(String.valueOf(petPayLoad.getId()));
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 3)
    public void verifyUploadPetImage() {
        logger.info("********* Uploading the pet image ***********");
        Response response = PetEndPoints.createPetImage(String.valueOf(petPayLoad.getId()));
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void verifyPetStatus() {
        logger.info("********* verifying the pet status ***********");
        Response response = PetEndPoints.getPetByStatus("pending");
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 5)
    public void verifyUpdatePet() {
        logger.info("********* Updating the pet ***********");
        Response response = PetEndPoints.updatePet(petPayLoad);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 6)
    public void verifyDeletePet() {
        logger.info("********* Deleting the pet ***********");
        Response response = PetEndPoints.deletePet(String.valueOf(petPayLoad.getId()));
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);

    }
}
