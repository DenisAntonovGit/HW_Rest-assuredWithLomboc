package test;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;


public class Specs {

    public static String createData = "{ \"name\" : \"Denis\", \"job\": \"QAA\" }";

    public static RequestSpecification getRequest = with()
            .baseUri("https://reqres.in")
            .basePath("/api")
            .log().all()
            .contentType(ContentType.JSON);

    public static RequestSpecification deleteRequest = with()
            .baseUri("https://reqres.in")
            .basePath("/api")
            .log().all()
            .contentType(ContentType.JSON);

    public static RequestSpecification postRequest = with()
            .baseUri("https://reqres.in")
            .body(createData)
            .basePath("/api")
            .log().all()
            .contentType(ContentType.JSON);

    public static RequestSpecification putRequest = with()
            .baseUri("https://reqres.in")
            .body(createData)
            .basePath("/api")
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification response200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification response201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();


    public static ResponseSpecification response204 = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .build();

    public static ResponseSpecification response404 = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .build();
}
