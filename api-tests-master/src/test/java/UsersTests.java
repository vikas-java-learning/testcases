import Models.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class UsersTests extends BaseTest {

    private Logger logger = Logger.getLogger(UsersTests.class);

    @Test
    public void getUsersTest() {
        JsonPath jsonPath = when().
                request("GET", "/users/1").jsonPath();
        HashMap data = jsonPath.getJsonObject("data");
        assertThat(data.get("last_name"), is("Bluth"));
    }

    @Test
    public void getUsersAsJson() throws JsonProcessingException {
        HashMap usersData = when().
                request("GET", "/users/1").jsonPath().getObject("data", HashMap.class);
        assertThat(usersData, is(notNullValue()));
    }

    @Test
    public void getUsersAsModel() throws JsonProcessingException {
        Users apiResponse = when().
                request("GET", "/users/2").
                then().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                jsonPath().
                getObject("data", Users.class);

        String name = apiResponse.getFirst_name();

        assertThat(name, is(notNullValue()));
        assertThat(name, isA(String.class));
//        more asserts here
    }

    @Test
    public void testUserNotFound() {
        when().request("GET", "/users/23").then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void testListOfUsers() {
        ArrayList<Users> users = when().
                request("GET", "/users?page=1").
                jsonPath().
                getObject("data", ArrayList.class);
        assertThat(users.size(), equalTo(6));
    }

    @Test
    public void testCreateUser() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "new-user");
        requestBody.put("job", "new-leader");
        Response response = given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(requestBody).
                post("/users");

        response.
                then().
                statusCode(HttpStatus.SC_CREATED).
                contentType(ContentType.JSON).
                body("id", Matchers.any(String.class)).
                and().
                body("name", is("new-user"));
    }

    @Test
    public void testUpdateUser() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("job", "new-leader-update");
        Response response = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(requestBody).
                put("/users/932");

        response.then().body("job", is("new-leader-update"));
    }

    @Test
    public void testDeleteUser() {
        Response response = given().delete("/users/2");
        response.then().statusCode(HttpStatus.SC_NO_CONTENT);
    }

}
