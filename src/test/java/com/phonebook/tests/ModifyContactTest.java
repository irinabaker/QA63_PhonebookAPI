package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.dto.ContactDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.phonebook.data.ObjectsData.auth;
import static com.phonebook.data.ObjectsData.contactDto;

public class ModifyContactTest extends TestBase {

    String id;
    ContactDto updated;
    String token;

    @BeforeMethod
    public void preRequest() {

        String responseToken = app.getUser().login(auth)
                .assertThat().statusCode(200)
                .extract().path("token");

        token = responseToken;

        String message = app.getContact().addContact(AUTH, token, contactDto)
                .assertThat().statusCode(200)
                .extract().path("message");

        String[] split = message.split(": ");
        id = split[1];

        updated = ContactDto.builder()
                .id(id)
                .name("Kristina")
                .lastName("Kan")
                .email("ken@gm.com")
                .phone("1234567890")
                .address("Moscow")
                .description("goalkeeper")
                .build();
    }

    @Test
    public void modifyContactSuccessTest() {

        String message = app.getContact().modifyContact(AUTH, token, updated)
                .assertThat().statusCode(200)
                .extract().path("message");
        System.out.println(message);

    }

}
