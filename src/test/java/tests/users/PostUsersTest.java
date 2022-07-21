package tests.users;

import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostUsersTest {

    private RequestSpecification requestSpec;

    @RegisterExtension
    static WireMockExtension wiremock = WireMockExtension.newInstance()
            .options(wireMockConfig()
                    .port(9999)
                    .extensions(new ResponseTemplateTransformer(true)))
            .build();

    @BeforeEach
    public void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://localhost").
                setPort(9999).
                setContentType(ContentType.JSON).
                build();
    }

    @Test
    public void createsANewUserFirstOption() {
        User userToCreate = new User("Potato Teste", "mrpotato", "potato@teste.com");

        given().
            spec(requestSpec).
        and().
            body(userToCreate).
        when().
            post("/user").
        then().
            log().all().
        and().
            assertThat().
            statusCode(201).
        and().
            body("name", equalTo(userToCreate.getName())).
        and().
            body("username", equalTo(userToCreate.getUsername()));
    }

    @Test
    public void createsANewUserSecondOption() {
        User userToCreate = new User("Potato Teste", "mrpotato", "potato@teste.com");

        User createdUser =

        given().
            spec(requestSpec).
        and().
            body(userToCreate).
        when().
            post("/users").
            as(User.class);

        assertEquals(userToCreate.getName(), createdUser.getName());
        assertEquals(userToCreate.getUsername(), createdUser.getUsername());
    }
}
