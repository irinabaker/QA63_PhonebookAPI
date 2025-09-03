package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.dto.AuthRequestDto;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTests extends TestBase {

    AuthRequestDto auth = AuthRequestDto.builder()
            .username("leno@gmail.com")
            .password("Bernd1234$")
            .build();

    @Test
    public void loginSuccessTest() {

        given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200);
    }
}
