package rest;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.get;


/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 05-03-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: rest
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public class KwetApiTest {

    @BeforeClass
    public static void setup() {

        RestAssured.baseURI = "http://localhost/";
        RestAssured.basePath = "/JEA6_war_exploded/api/kwets";

        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void getAllKwets() {
        get("/all").then().assertThat().statusCode(200);
    }

    @Test
    public void getById() {
        get("/id/0").then().assertThat().statusCode(406); // NOT_ACCEPTABLE
        get("/id/1").then().assertThat().statusCode(200);
        get("/id/1000000").then().assertThat().statusCode(204); // NO_CONTENT
    }

    @Test
    public void getByText() {
        get("/text/test").then().assertThat().statusCode(200);
        get("/text/unknown_text").then().assertThat().statusCode(204); // NO_CONTENT
    }

    @Test
    public void getAllKwetsFromOwner() {
        get("/owner/1/all").then().assertThat().statusCode(200); // Shoud return at least 1 kwet
        // Make sure that Account id 1000000 is not taken
        get("/owner/1000000/all").then().assertThat().statusCode(204); // NO_CONTENT
        get("/owner/0/all").then().assertThat().statusCode(406); // NOT_ACCEPTABLE

    }

    @Test
    public void getAllKwetsFromOwnerByText() {
        get("/owner/1/text/test").then().assertThat().statusCode(200); // Should return at least 1 kwet
        get("/owner/1/text/unknown_text").then().assertThat().statusCode(204); // NO_CONTENT
        get("/owner/1000000/text/test").then().assertThat().statusCode(204); // NO_CONTENT
        get("/owner/0/text/test").then().assertThat().statusCode(406); // NOT ACCEPTABLE
    }

    @Test
    public void createKwet() {
    }

    @Test
    public void updateKwet() {
    }
}