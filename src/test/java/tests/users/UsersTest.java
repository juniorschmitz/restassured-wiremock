package tests.users;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@WireMockTest(httpPort = 9999)
public class UsersTest {

    private RequestSpecification requestSpec;

    @BeforeEach
    public void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://localhost").
                setPort(9999).
                setContentType(ContentType.JSON).
                build();
    }

    @Test
    public void getUsersReturnsUsersList() {
        String expectedName = "Bruce Wayne";
        given().
            spec(requestSpec).
        when().
            get("/users").
        then().
            log().all().
        and().
            assertThat().
            statusCode(200).
        and().
            body("[0].name", equalTo(expectedName));
    }

    @Test
    public void createsANewUser() {
        User userToCreate = new User("Potato Teste", "mrpotato", "potato@teste.com");

        given().
            spec(requestSpec).
        and().
            body(userToCreate).
        when().
            post("/users").
        then().
            log().all().
        and().
            assertThat().
            statusCode(201).
        and().
            body("name", equalTo(userToCreate.getName())).
        and().
            body("username", equalTo(userToCreate.getUserName()));
    }

}
