package com.safetrust.interview.contact.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = Contact.TABLE_NAME)
@NoArgsConstructor
public class Contact implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "contact";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true,nullable = false)
    private String name;

    @NotNull
    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @NotNull
    @Column(name = "telephone_number",nullable = false)
    private String telephoneNumber;

    @NotNull
    @Column(name = "postal_address",nullable = false)
    private String postalAddress;

	public Contact(long id, @NotNull String name, @NotNull String emailAddress, @NotNull String telephoneNumber,
			@NotNull String postalAddress) {
		super();
		this.id = id;
		this.name = name;
		this.emailAddress = emailAddress;
		this.telephoneNumber = telephoneNumber;
		this.postalAddress = postalAddress;
	}
    
    

}
