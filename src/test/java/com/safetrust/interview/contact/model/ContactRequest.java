package com.safetrust.interview.contact.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {

    private long id;

    private String name;

    private String emailAddress;

    private String telephoneNumber;

    private String postalAddress;

}
