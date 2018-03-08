package restassured;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;


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
public class AccountApiTest {

    @BeforeClass
    public static void setup() {


        RestAssured.baseURI = "http://localhost/";
        RestAssured.basePath = "/JEA6_war_exploded/api/accounts";

        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void getAllAccounts() {
        get("/all").then().body("email", hasItems("juleskreutzer@me.com", "jules@nujules.nl"));
    }

    @Test
    public void findAccountByEmail() {
        get("/email/juleskreutzer@me.com").then().body("email", equalTo("juleskreutzer@me.com")).body("id", equalTo(1));

        get("/email/unknown@email.com").then().assertThat().statusCode(204); // NO_CONTENT
    }

    @Test
    public void findAccountById() {
        get("/id/1").then().body("email", equalTo("juleskreutzer@me.com"));

        get("/id/0").then().assertThat().statusCode(406); // NOT_ACCEPTABLE

        // Make sure that there is no user with id 1000000 or this test will fail
        get("/id/1000000").then().assertThat().statusCode(204); // NO_CONTENT
    }

    @Test
    public void findAccountByFullName() {
//        get("/fullname/Jules%20Kreutzer").then().body("email", equalTo("juleskreutzer@me.com"));
//
//        get("/fullname/unknown%20name").then().assertThat().statusCode(204); // NO_CONTENT
    }

    @Test
    public void createAccount() {
    }

    @Test
    public void updateAccount() {
    }

    @Test
    public void getFollowers() {
    }

    @Test
    public void getFollowing() {
    }
}
