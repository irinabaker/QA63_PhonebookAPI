package com.phonebook.core;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    @BeforeMethod
    public void init() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }
}
