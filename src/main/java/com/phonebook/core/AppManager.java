package com.phonebook.core;

import com.phonebook.endpoints.ContactHelper;
import com.phonebook.endpoints.UserHelper;
import com.phonebook.utils.PropertiesLoader;
import io.restassured.RestAssured;

public class AppManager {

    UserHelper user;
    ContactHelper contact;

    public void start() {
        RestAssured.baseURI = PropertiesLoader.loadProperty("uri");
        RestAssured.basePath = PropertiesLoader.loadProperty("path");

        user = new UserHelper();
        contact = new ContactHelper();
    }

    public UserHelper getUser() {
        return user;
    }

    public ContactHelper getContact() {
        return contact;
    }
}
