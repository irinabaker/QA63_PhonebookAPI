package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.dto.ContactDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.phonebook.data.ObjectsData.auth;
import static com.phonebook.data.ObjectsData.contactDto;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class AddContactTests extends TestBase {

    String token;

    @BeforeMethod
    public void preRequest() {

        String responseToken = app.getUser().login(auth)
                .assertThat().statusCode(200)
                .extract().path("token");

        token = responseToken;
    }

    @Test
    public void addContactSuccessTest() {
        // String message =
        app.getContact().addContact(AUTH, token, contactDto)
                .assertThat().statusCode(200)
                .assertThat().body("message", containsString("Contact was added!"));
        //  .extract().path("message");
        //   System.out.println(message);
    }

    @Test
    public void addContactWithoutNameTest() {

        //   ErrorDto errorDto =
        app.getContact().addContact(AUTH,token,ContactDto.builder()
                .lastName("Kan")
                .email("kan@gm.com")
                .phone("1234567890")
                .address("Berlin")
                .description("goalkeeper")
                .build())
                .assertThat().statusCode(400)
                .assertThat().body("message.name", containsString("must not be blank"));
        // .extract().response().as(ErrorDto.class);
        //  System.out.println(errorDto.getError() + " *** " + errorDto.getMessage());
    }

    @Test
    public void addContactWithInvalidPhoneTest() {
       // ErrorDto errorDto =
        app.getContact().addContact(AUTH,token,ContactDto.builder()
                .name("Oliver")
                .lastName("Kan")
                .email("kan@gm.com")
                .phone("123456")
                .address("Berlin")
                .description("goalkeeper")
                .build())
                .assertThat().statusCode(400)
                        .assertThat().body("message.phone",equalTo("Phone number must contain only digits! And length min 10, max 15!"));
//                .extract().response().as(ErrorDto.class);
//        System.out.println(errorDto.getMessage());
    }
}

// Bad Request *** {name=must not be blank}
// {phone=Phone number must contain only digits! And length min 10, max 15!}

//Contact was added! ID: a9584cac-5877-44c6-ad00-42fab65ca3ec

// [0]: [1]
