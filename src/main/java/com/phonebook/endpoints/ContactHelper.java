package com.phonebook.endpoints;

import com.phonebook.dto.ContactDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ContactHelper {

    public ValidatableResponse getContacts(String auth, String userToken) {
        return given()
                .header(auth, userToken)
                .when()
                .get("contacts")
                .then();
    }

    public ValidatableResponse addContact(String auth, String userToken, ContactDto contactData) {
        return given()
                .contentType(ContentType.JSON)
                .body(contactData)
                .header(auth, userToken)
                .when()
                .post("contacts")
                .then();
    }

    public ValidatableResponse deleteContactById(String auth, String userToken, String contactId) {
        return given()
                .header(auth, userToken)
                .when()
                .delete("contacts/" + contactId)
                .then();
    }

    public ValidatableResponse modifyContact(String auth, String userToken, ContactDto updatedContact) {
        return given()
                .header(auth, userToken)
                .contentType(ContentType.JSON)
                .body(updatedContact)
                .when()
                .put("contacts")
                .then();
    }
}
