package com.safetrust.interview.contact.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = Contact.TABLE_NAME)
public class Contact {

    public static final String TABLE_NAME = "contact";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String name;

    @NotNull
    @Column(name = "email_address", unique = true, nullable = false)
    private String emailAddress;

    @NotNull
    @Column(name = "telephone_number", unique = true, nullable = false)
    private String telephoneNumber;

    @NotNull
    @Column(name = "postal_address", unique = true, nullable = false)
    private String postalAddress;

}
