package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.dto.AuthRequestDto;
import com.phonebook.dto.AuthResponseDto;
import com.phonebook.dto.ErrorDto;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginTests extends TestBase {

    AuthRequestDto auth = AuthRequestDto.builder()
            .username("leno@gmail.com")
            .password("Bernd1234$")
            .build();

    @Test
    public void loginSuccessTest() {

        AuthResponseDto token = given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDto.class);
        System.out.println(token.getToken());
    }

    @Test
    public void loginSuccessTest2() {
        String token = given()
                .contentType(ContentType.JSON)
                .body(auth)
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract().path("token");
        System.out.println(token);
    }

    //assert with TestNG
    @Test
    public void loginWithWrongPasswordTest() {
        ErrorDto errorDto =
                given()
                .body(AuthRequestDto.builder()
                        .username("leno@gmail.com")
                        .password("bernd1234$")
                        .build())
                .contentType(ContentType.JSON)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
               .extract().response().as(ErrorDto.class);

        Assert.assertEquals(errorDto.getError(),"Unauthorized");
       // System.out.println(errorDto.getError() + " *** " + errorDto.getMessage());
// Unauthorized *** Login or Password incorrect
    }

    //assert with RestAssured
    @Test
    public void loginWithWrongPasswordTest2() {

        given()
                .body(AuthRequestDto.builder()
                        .username("leno@gmail.com")
                        .password("bernd1234$")
                        .build())
                .contentType(ContentType.JSON)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("message",equalTo("Login or Password incorrect"));
    }
}

//  eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibGVub0BnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTc1NzU3MjMwMSwiaWF0IjoxNzU2OTcyMzAxfQ.Jf12HYvhCi_ZGFVdx_1rj35ICcaw8aZVYHjRSfpL-Nk
