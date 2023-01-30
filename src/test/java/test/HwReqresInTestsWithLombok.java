package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.lombok.CreateModel;
import test.lombok.ListOfUsers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static test.Specs.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HwReqresInTestsWithLombok {

    @Test
    @DisplayName("Check success creating")
    void successfulCreating() {
        CreateModel createModel = given()
                .spec(postRequest)
                .when()
                .post("/users")
                .then()
                .spec(response201)
                .log().status()
                .log().body()
                .extract().as(CreateModel.class);
        assertEquals("Denis", createModel.getName());
        assertEquals("QAA", createModel.getJob());
    }

    @Test
    void getListOfUsersWithGroovy() {
        ListOfUsers listOfUsers = given()
                .spec(getRequest)
                .when()
                .get("/users?page=2")
                .then()
                .spec(response200)
                .log().status()
                .body("data.findAll{it.id == 8}.last_name.flatten()",
                        hasItem("Ferguson"))
                .extract().as(ListOfUsers.class);
        assertEquals(2, listOfUsers.getPage());
        assertEquals(12, listOfUsers.getTotal());
        assertEquals(2, listOfUsers.getTotalPages());
    }

    @Test
    void singleResourceNotFound() {
        given()
                .spec(getRequest)
                .when()
                .get("/unknown/23")
                .then()
                .log().status()
                .log().body()
                .spec(response404);
    }
    @Test
    void updateData() {
        CreateModel createModel = given()
                .spec(putRequest)
                .when()
                .put("/users/5")
                .then()
                .log().status()
                .log().body()
                .spec(response200)
                .extract().as(CreateModel.class);
        assertEquals("Denis", createModel.getName());
        assertEquals("QAA", createModel.getJob());
    }

    @Test
    void deleteUser() {
        given()
                .spec(deleteRequest)
                .when()
                .delete("/users/5")
                .then()
                .log().status()
                .spec(response204);
    }
}
