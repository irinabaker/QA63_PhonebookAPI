package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.dto.AuthRequestDto;
import com.phonebook.dto.AuthResponseDto;
import com.phonebook.dto.ErrorDto;
import com.phonebook.utils.MyDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.phonebook.data.ObjectsData.auth;
import static org.hamcrest.Matchers.equalTo;

public class LoginTests extends TestBase {

    @Test
    public void loginSuccessTest() {
        AuthResponseDto token = app.getUser().login(auth)
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDto.class);
        System.out.println(token.getToken());
    }

    @Test
    public void loginSuccessTest2() {
        String token = app.getUser().login(auth)
                .assertThat().statusCode(200)
                .extract().path("token");
       System.out.println(token);
    }

    //assert with TestNG
    @Test(dataProviderClass = MyDataProvider.class,dataProvider = "addNewContactFromCsv")
    public void loginWithWrongPasswordTest(AuthRequestDto authRequestDto) {
        ErrorDto errorDto = app.getUser().login(authRequestDto)
                .assertThat().statusCode(401)
               .extract().response().as(ErrorDto.class);

        Assert.assertEquals(errorDto.getError(),"Unauthorized");
       // System.out.println(errorDto.getError() + " *** " + errorDto.getMessage());
// Unauthorized *** Login or Password incorrect
    }

    //assert with RestAssured
    @Test
    public void loginWithWrongPasswordTest2() {
        app.getUser().login(AuthRequestDto.builder()
                .username("leno@gmail.com")
                .password("bernd1234$")
                .build())
                .assertThat().statusCode(401)
                .assertThat().body("message",equalTo("Login or Password incorrect"));
    }
}

//  eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibGVub0BnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTc1NzU3MjMwMSwiaWF0IjoxNzU2OTcyMzAxfQ.Jf12HYvhCi_ZGFVdx_1rj35ICcaw8aZVYHjRSfpL-Nk
