package com.phonebook.data;

import com.phonebook.dto.ContactDto;

public class ObjectsData {

    public static ContactDto contactDto = ContactDto.builder()
            .name("Oliver")
            .lastName("Kan")
            .email("kan@gm.com")
            .phone("1234567890")
            .address("Berlin")
            .description("goalkeeper")
            .build();
}
